package com.example.ndahiroloicke.controller;

import com.example.ndahiroloicke.dto.BookDTO;
import com.example.ndahiroloicke.model.Book;
import com.example.ndahiroloicke.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.createBook(bookDTO));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

    @GetMapping("/{isbn}/availability")
    public ResponseEntity<Book.AvailabilityStatus> getBookAvailability(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookAvailability(isbn));
    }
} 