package com.example.ndahiroloicke.dto;

import com.example.ndahiroloicke.model.BorrowingTransaction.TransactionStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BorrowingTransactionDTO {
    private Long id;
    private Long bookId;
    private String borrowerName;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private TransactionStatus status;
} 