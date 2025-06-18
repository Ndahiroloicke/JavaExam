package com.example.ndahiroloicke.service;

import com.example.ndahiroloicke.dto.BorrowingTransactionDTO;
import com.example.ndahiroloicke.model.Book;
import com.example.ndahiroloicke.model.BorrowingTransaction;
import com.example.ndahiroloicke.repository.BookRepository;
import com.example.ndahiroloicke.repository.BorrowingTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class BorrowingTransactionService {

    @Autowired
    private BorrowingTransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    public BorrowingTransactionDTO createBorrowingTransaction(String isbn, String borrowerName) {
        Book book = bookRepository.findByIsbn(isbn)
            .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + isbn));

        if (book.getAvailabilityStatus() != Book.AvailabilityStatus.AVAILABLE) {
            throw new RuntimeException("Book is not available for borrowing");
        }

        BorrowingTransaction transaction = new BorrowingTransaction();
        transaction.setBook(book);
        transaction.setBorrowerName(borrowerName);
        transaction.setBorrowDate(LocalDateTime.now());
        transaction.setStatus(BorrowingTransaction.TransactionStatus.PENDING);

        BorrowingTransaction savedTransaction = transactionRepository.save(transaction);
        
        // Update book availability
        bookService.updateBookAvailability(book.getId(), Book.AvailabilityStatus.BORROWED);

        return convertToDTO(savedTransaction);
    }

    public BorrowingTransactionDTO returnBook(Long transactionId) {
        BorrowingTransaction transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));

        if (transaction.getStatus() == BorrowingTransaction.TransactionStatus.RETURNED) {
            throw new RuntimeException("Book has already been returned");
        }

        transaction.setReturnDate(LocalDateTime.now());
        transaction.setStatus(BorrowingTransaction.TransactionStatus.RETURNED);
        
        // Update book availability
        bookService.updateBookAvailability(transaction.getBook().getId(), Book.AvailabilityStatus.AVAILABLE);

        return convertToDTO(transactionRepository.save(transaction));
    }

    private BorrowingTransactionDTO convertToDTO(BorrowingTransaction transaction) {
        BorrowingTransactionDTO dto = new BorrowingTransactionDTO();
        dto.setId(transaction.getId());
        dto.setBookId(transaction.getBook().getId());
        dto.setBorrowerName(transaction.getBorrowerName());
        dto.setBorrowDate(transaction.getBorrowDate());
        dto.setReturnDate(transaction.getReturnDate());
        dto.setStatus(transaction.getStatus());
        return dto;
    }
} 