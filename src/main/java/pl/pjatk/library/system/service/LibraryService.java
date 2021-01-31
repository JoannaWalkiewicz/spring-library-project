package pl.pjatk.library.system.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.pjatk.library.model.Author;
import pl.pjatk.library.model.Book;
import pl.pjatk.library.model.LibraryCard;
import pl.pjatk.library.model.Reader;
import pl.pjatk.library.system.repository.AuthorRepository;
import pl.pjatk.library.system.repository.BookRepository;
import pl.pjatk.library.system.repository.LibraryCardRepository;
import pl.pjatk.library.system.repository.ReaderRepository;

import java.util.List;


@Service
public class LibraryService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private LibraryCardRepository libraryCardRepository;
    private ReaderRepository readerRepository;

    public LibraryService(BookRepository bookRepository, AuthorRepository authorRepository, LibraryCardRepository libraryCardRepository, ReaderRepository readerRepository ) {
        this.authorRepository = authorRepository;
        this.bookRepository  = bookRepository;
        this.libraryCardRepository = libraryCardRepository;
        this.readerRepository = readerRepository;

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


}
