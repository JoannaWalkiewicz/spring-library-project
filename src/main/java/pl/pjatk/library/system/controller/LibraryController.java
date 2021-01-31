package pl.pjatk.library.system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.library.model.Author;
import pl.pjatk.library.model.Book;
import pl.pjatk.library.model.LibraryCard;
import pl.pjatk.library.model.Reader;
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
    public ResponseEntity addBook(@RequestBody Book book) {
        try {
            return ResponseEntity.ok(libraryService.addBook(book));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/author/add")
    public ResponseEntity addAuthor(@RequestBody Author author) {
        try {
            return ResponseEntity.ok(libraryService.addAuthor(author));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/book")
    public ResponseEntity getBooks(){
        try {
            return ResponseEntity.ok(ResponseEntity.ok(libraryService.getAllBooks()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
    @PostMapping("/reader/add")
    public ResponseEntity addReader(@RequestBody Reader reader) {
        try {
            return ResponseEntity.ok(libraryService.addReader(reader));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
    @PostMapping("/libraryCard/add")
    public ResponseEntity addLibraryCard(@RequestBody LibraryCard libraryCard) {
        try {
            return ResponseEntity.ok(libraryService.addLibraryCard(libraryCard));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
