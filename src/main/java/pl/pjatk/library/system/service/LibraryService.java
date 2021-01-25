package pl.pjatk.library.system.service;

import org.springframework.stereotype.Service;
import pl.pjatk.library.model.Book;
import pl.pjatk.library.system.repository.BookRepository;

@Service
public class LibraryService {
    public BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
}
