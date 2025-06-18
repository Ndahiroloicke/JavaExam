package com.example.ndahiroloicke.service;

import com.example.ndahiroloicke.dto.BookDTO;
import com.example.ndahiroloicke.model.Book;
import com.example.ndahiroloicke.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setAvailabilityStatus(bookDTO.getAvailabilityStatus());
        
        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }

    public BookDTO getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
            .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + isbn));
        return convertToDTO(book);
    }

    public Book.AvailabilityStatus getBookAvailability(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
            .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + isbn));
        return book.getAvailabilityStatus();
    }

    public void updateBookAvailability(Long bookId, Book.AvailabilityStatus status) {
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));
        book.setAvailabilityStatus(status);
        bookRepository.save(book);
    }

    private BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setAvailabilityStatus(book.getAvailabilityStatus());
        return dto;
    }
} 