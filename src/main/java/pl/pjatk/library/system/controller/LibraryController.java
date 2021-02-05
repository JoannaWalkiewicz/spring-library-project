package pl.pjatk.library.system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.library.model.*;
import pl.pjatk.library.system.service.LibraryService;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    private LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/book/borrow")
    public ResponseEntity borrowBook(@RequestBody BorrowBookRequest borrowBookRequest) {
        return ResponseEntity.ok(libraryService.borrowBook(borrowBookRequest));
    }

    @PostMapping("/book/return")
    public ResponseEntity returnBook(@RequestBody BorrowBookRequest borrowBookRequest) {
        return ResponseEntity.ok(libraryService.returnBook(borrowBookRequest));
    }

    @PostMapping("/book/add")
    public ResponseEntity addBook(@RequestBody Book book) {
        return ResponseEntity.ok(libraryService.addBook(book));
    }

    @PostMapping("/author/add")
    public ResponseEntity addAuthor(@RequestBody Author author) {
        return ResponseEntity.ok(libraryService.addAuthor(author));
    }

    @GetMapping("/book")
    public ResponseEntity getBooks() {
        return ResponseEntity.ok(ResponseEntity.ok(libraryService.getAllBooks()));
    }

    @PostMapping("/reader/add")
    public ResponseEntity addReader(@RequestBody Reader reader) {
        return ResponseEntity.ok(libraryService.addReader(reader));
    }

    @PostMapping("/libraryCard/add")
    public ResponseEntity addLibraryCard(@RequestBody LibraryCard libraryCard) {
        return ResponseEntity.ok(libraryService.addLibraryCard(libraryCard));
    }

    @PostMapping("/librarian/add")
    public ResponseEntity addLibrarian(@RequestBody Librarian librarian) {
        try {
            return ResponseEntity.ok(libraryService.addLibrarian(librarian));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/book/find/{title}")
    public ResponseEntity findBookByTitle(@PathVariable String title) {
        return ResponseEntity.ok(libraryService.findBookByTitle(title));
    }
}
