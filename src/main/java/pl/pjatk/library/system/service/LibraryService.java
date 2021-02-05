package pl.pjatk.library.system.service;

import org.apache.catalina.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.pjatk.library.model.*;
import pl.pjatk.library.system.repository.*;

import javax.swing.text.html.Option;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class LibraryService {
    private LibrarianRepository librarianRepository;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private LibraryCardRepository libraryCardRepository;
    private ReaderRepository readerRepository;
    private BorrowBookRepository borrowBookRepository;


    public LibraryService(BookRepository bookRepository, AuthorRepository authorRepository, LibraryCardRepository libraryCardRepository, ReaderRepository readerRepository, LibrarianRepository librarianRepository, BorrowBookRepository borrowBookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository  = bookRepository;
        this.libraryCardRepository = libraryCardRepository;
        this.readerRepository = readerRepository;
        this.librarianRepository = librarianRepository;
        this.borrowBookRepository = borrowBookRepository;

    }

    public Response<Book> addBook(Book book) {
        if(this.bookRepository.existsBookByReferenceId(book.getReferenceId())){
            return new Response<>("Nie można dodać książki ponieważ istnieje już w bazie", null);
        }
        return new Response<>(null, this.bookRepository.save(book));
    }

    public Response<List<Book>> getAllBooks() {
        return new Response<>(null, this.bookRepository.findAll());
    }

    public Response<Author> addAuthor(Author author) {
        return new Response<>(null, this.authorRepository.save(author));
    }

    public Response<LibraryCard> addLibraryCard(LibraryCard libraryCard) {
        return new Response<>(null, this.libraryCardRepository.save(libraryCard));
    }

    public Response<Reader> addReader(Reader reader) {
        if(this.readerRepository.existsByPesel(reader.getPesel())){
            return new Response<>("Użytkownik o takim numerze pesel istnieje już w bazie", null);
        }
        return new Response<>(null, this.readerRepository.save(reader));
    }

    public Response<Librarian> addLibrarian(Librarian librarian) {
        return  new Response<>(null, this.librarianRepository.save(librarian));
    }

    public Response<Boolean> borrowBook(BorrowBookRequest borrowBookRequest) {
        Optional<Reader> readerOptional = this.readerRepository.findById(borrowBookRequest.getReaderId());
        Optional<Librarian> librarianOptional = this.librarianRepository.findById(borrowBookRequest.getLibrarianId());
        Optional<Book> bookOptional = this.bookRepository.findById(borrowBookRequest.getBookId());

        if (readerOptional.isPresent() && librarianOptional.isPresent() && bookOptional.isPresent()) {
            Reader reader = readerOptional.get();
            LibraryCard libraryCard = reader.getLibraryCard();

            // check if reader has valid library card
            LocalDate date = LocalDate.now();
            if (!date.isBefore(libraryCard.getValidFrom()) && (date.isBefore(libraryCard.getValidTo())))
            {
                // check if book is available to borrow
                Book book = bookOptional.get();
                if(book.getStatus() == EnBookStatus.Free) {
                    this.borrowBookRepository.save(new BorrowBook(date, null, bookOptional.get(), librarianOptional.get(), readerOptional.get()));
                    // update book stats to InUse
                    this.bookRepository.setBookStatus(EnBookStatus.InUse, bookOptional.get().getId());
                } else {
                    return new Response<>("Podana książka jest wypożyczona, nie można wypożyczyć", null);

                }

                return new Response<>(null, true);
            } else {
                return new Response<>("Użytkownik nie może wypożyczyć książki ponieważ nie ma ważnej karty bibliotecznej", null);
            }

        } else {
            return new Response<>("Błędne parametry wejściowe", null);
        }
    }

    public Response<Boolean> returnBook(BorrowBookRequest borrowBookRequest) {
        Optional<Reader> readerOptional = this.readerRepository.findById(borrowBookRequest.getReaderId());
        Optional<Librarian> librarianOptional = this.librarianRepository.findById(borrowBookRequest.getLibrarianId());
        Optional<Book> bookOptional = this.bookRepository.findById(borrowBookRequest.getBookId());
        LocalDate dateOfReturn = LocalDate.now();
        if (readerOptional.isPresent() && librarianOptional.isPresent() && bookOptional.isPresent()) {
            BorrowBook borrowBook = this.borrowBookRepository.getBorrowBookByBookId(borrowBookRequest.getBookId());
            long daysBetween = Duration.between(borrowBook.getDateOfBorrow().atStartOfDay(), dateOfReturn.atStartOfDay()).toDays();

            // if book was returned after more than 7 days it should pay extra charge
            if (daysBetween > 7) {
                Reader reader = readerOptional.get();
                LibraryCard libraryCard = reader.getLibraryCard();
                // get actual balance
                Double actualBalance = libraryCard.getBalance();
                this.libraryCardRepository.setBalanace(actualBalance - 10L, libraryCard.getId());
            }
            // update return date in borrow book repository
            this.borrowBookRepository.setBorrowBookReturnDate(dateOfReturn, borrowBook.getId());

            // update book status to Free
            this.bookRepository.setBookStatus(EnBookStatus.Free, bookOptional.get().getId());

            return new Response<>(null, true);
        } else {
            return new Response<>("Błędne parametry wejściowe", null);
        }

    }

    public Response<List<Book>> findBookByTitle(String title) {
        List<Book> books = this.bookRepository.findByTitle(title);
        if (!books.isEmpty()) {
            return new Response<>(null, books);
        } else {
            return new Response<>("Nie ma takich książek o takim tytule", null);
        }
    }
}
