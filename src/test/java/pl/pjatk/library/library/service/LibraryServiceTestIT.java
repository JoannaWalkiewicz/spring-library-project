package pl.pjatk.library.library.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pjatk.library.model.Author;
import pl.pjatk.library.model.Book;
import pl.pjatk.library.model.Librarian;
import pl.pjatk.library.system.service.LibraryService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LibraryServiceTestIT {
    @Autowired
    private LibraryService libraryService;

    @Test
    void shoudReturnAllBooksTest(){
        List<Book> all = libraryService.getAllBooks();
        assertThat(all).isEmpty();
    }
    @Test
    void shouldAddLibrarianToDatabaseTest() {
        Librarian librarian = new Librarian(1L, "Name", "Surname");
        Librarian savedLibrarian = libraryService.addLibrarian(librarian);
        assertThat(savedLibrarian.getId()).isNotNull();
    }
}
