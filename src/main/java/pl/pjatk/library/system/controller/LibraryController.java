package pl.pjatk.library.system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.library.model.Book;
import pl.pjatk.library.system.service.LibraryService;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    private LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;         //przypisanie referencji polom do tego obiektu; wstrzykiwanie zależności
    }

    @PostMapping("/book/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(libraryService.addBook(book));
    }

}
