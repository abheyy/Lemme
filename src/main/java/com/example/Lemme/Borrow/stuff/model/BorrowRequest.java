package com.example.Lemme.Borrow.stuff.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "borrow_requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowRequest {
    @Id
    private String id;
    private String itemId;
    private String borrowerId;
    private String lenderId;
    private BorrowStatus status; // PENDING, APPROVED, REJECTED, RETURNED
    private LocalDateTime requestDate = LocalDateTime.now();
    private LocalDateTime returnDate; // expected return
}

