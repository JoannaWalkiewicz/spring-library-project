package pl.pjatk.library.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.library.model.Author;
import pl.pjatk.library.model.Book;
import pl.pjatk.library.model.EnBookStatus;
import pl.pjatk.library.model.EnBookType;
import pl.pjatk.library.system.repository.*;
import pl.pjatk.library.system.service.LibraryService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {
    @Mock
    private LibrarianRepository librarianRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private LibraryCardRepository libraryCardRepository;
    @Mock
    private ReaderRepository readerRepository;
    @Mock
    private BorrowBookRepository borrowBookRepository;
    @InjectMocks
    private LibraryService libraryService;

    @Test
    void findAllTest() {
        //Given
        ArrayList<Book> books = new ArrayList<>();
        Book book = new Book(1L, "Book title", 2000, "A03", EnBookType.Romance, EnBookStatus.Free, new ArrayList<>());
        books.add(book);
        when(bookRepository.findAll()).thenReturn(books);
        //When
        List<Book> all = libraryService.getAllBooks();
        //Then
        assertThat(all).isNotEmpty();
    }
    @Test
    void addBookWithExistingReferenceIdShouldThrowException(){
        //Given
        ArrayList<Book> books = new ArrayList<>();
        Book book = new Book(1L, "Book title", 2000, "A03", EnBookType.Romance, EnBookStatus.Free, new ArrayList<>());
        books.add(book);
        //When
        when(bookRepository.existsBookByReferenceId(book.getReferenceId())).thenReturn(true);
        //Then)
        Assertions.assertThrows(Exception.class, () -> libraryService.addBook(book));
    }

    @Test
    void addBookWithUniqueReferenceIdShouldAddBook() throws Exception {
        //Given
        ArrayList<Book> books = new ArrayList<>();
        Book book = new Book(1L, "Book title", 2000, "A03", EnBookType.Romance, EnBookStatus.Free, new ArrayList<>());
        books.add(book);
        //When
        when(bookRepository.save(book)).thenReturn(book);
        when(bookRepository.existsBookByReferenceId(book.getReferenceId())).thenReturn(false);
        //Then
        Book addedBook = libraryService.addBook(book);
        assertThat(addedBook).isNotNull();
    }
}
