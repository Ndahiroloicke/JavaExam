package com.example.ndahiroloicke.repository;

import com.example.ndahiroloicke.model.BorrowingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BorrowingTransactionRepository extends JpaRepository<BorrowingTransaction, Long> {
    List<BorrowingTransaction> findByBookId(Long bookId);
    List<BorrowingTransaction> findByBorrowerName(String borrowerName);
} 