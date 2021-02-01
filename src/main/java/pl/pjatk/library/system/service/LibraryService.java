package pl.pjatk.library.system.service;

import org.apache.catalina.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.pjatk.library.model.*;
import pl.pjatk.library.system.repository.*;

import javax.swing.text.html.Option;
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

    public LibraryService(BookRepository bookRepository, AuthorRepository authorRepository, LibraryCardRepository libraryCardRepository, ReaderRepository readerRepository, LibrarianRepository librarianRepository, BorrowBookRepository borrowBookRepository ) {
        this.authorRepository = authorRepository;
        this.bookRepository  = bookRepository;
        this.libraryCardRepository = libraryCardRepository;
        this.readerRepository = readerRepository;
        this.librarianRepository = librarianRepository;
        this.borrowBookRepository = borrowBookRepository;
    }

    public Book addBook(Book book) throws Exception {
        if(this.bookRepository.existsBookByReferenceId(book.getReferenceId())){
            throw new Exception("Nie można dodać książki ponieważ istnieje już w bazie");
        }
        return this.bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    public Author addAuthor(Author author) {
        return this.authorRepository.save(author);
    }

    public LibraryCard addLibraryCard(LibraryCard libraryCard) {
        return this.libraryCardRepository.save(libraryCard);
    }
    public Reader addReader(Reader reader) {
        return this.readerRepository.save(reader);
    }

    public Librarian addLibrarian(Librarian librarian) {
        return this.librarianRepository.save(librarian);
    }

    public boolean borrowBook(BorrowBookRequest borrowBookRequest) throws Exception {
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
                // ...
                this.borrowBookRepository.save(new BorrowBook(date, date.plusDays(7), bookOptional.get(), librarianOptional.get(), readerOptional.get()));

                // update book status to InUse
                // ...

                return true;
            } else {
                throw new Exception("Użytkownik nie może wypożyczyć książki ponieważ nie ma ważnej karty bibliotecznej");
            }

        } else {
            throw new Exception("Błędne parametry wejściowe");
        }
    }
}
