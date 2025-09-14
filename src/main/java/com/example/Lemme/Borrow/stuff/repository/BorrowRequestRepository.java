package com.example.Lemme.Borrow.stuff.repository;

import com.example.Lemme.Borrow.stuff.model.BorrowRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BorrowRequestRepository extends MongoRepository<BorrowRequest, String> {
    List<BorrowRequest> findByBorrowerId(String borrowerId);
    List<BorrowRequest> findByLenderId(String lenderId);
    List<BorrowRequest> findByItemId(String itemId);
}