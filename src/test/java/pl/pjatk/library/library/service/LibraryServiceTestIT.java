package pl.pjatk.library.library.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pjatk.library.model.*;
import pl.pjatk.library.system.service.LibraryService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LibraryServiceTestIT {
    @Autowired
    private LibraryService libraryService;

    @Test
    void shoudReturnAllBooksTest(){
        Response<List<Book>> all = libraryService.getAllBooks();
        assertThat(all.getResult()).isEmpty();
    }
    @Test
    void shouldAddLibrarianToDatabaseTest() {
        Librarian librarian = new Librarian(1L, "Name", "Surname");
        Response<Librarian> response = libraryService.addLibrarian(librarian);
        assertThat(response.getResult().getId()).isNotNull();
    }
    @Test
    void shouldFindBookByTitle() throws Exception {
        String title = "testowy";
        Book book = new Book(1L, title, 1991,"A03", EnBookType.Fantasy, EnBookStatus.Free, null);
        Response<Book> addedBook = libraryService.addBook(book);
        Response<List<Book>> books = libraryService.findBookByTitle(title);
        assertThat(books.getResult()).isNotEmpty();
    }
}
