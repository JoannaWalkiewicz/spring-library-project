package pl.pjatk.library.system.service;

import org.springframework.stereotype.Service;
import pl.pjatk.library.model.Book;
import pl.pjatk.library.system.repository.BookRepository;

import java.util.List;


@Service
public class LibraryService {
    private BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository ) {
        this.bookRepository  = bookRepository;
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
}
