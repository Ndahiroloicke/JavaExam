package com.example.ndahiroloicke.controller;

import com.example.ndahiroloicke.dto.BorrowingTransactionDTO;
import com.example.ndahiroloicke.service.BorrowingTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class BorrowingTransactionController {

    @Autowired
    private BorrowingTransactionService transactionService;

    @PostMapping("/borrow")
    public ResponseEntity<BorrowingTransactionDTO> borrowBook(
            @RequestParam String isbn,
            @RequestParam String borrowerName) {
        return ResponseEntity.ok(transactionService.createBorrowingTransaction(isbn, borrowerName));
    }

    @PostMapping("/{transactionId}/return")
    public ResponseEntity<BorrowingTransactionDTO> returnBook(@PathVariable Long transactionId) {
        return ResponseEntity.ok(transactionService.returnBook(transactionId));
    }
} 