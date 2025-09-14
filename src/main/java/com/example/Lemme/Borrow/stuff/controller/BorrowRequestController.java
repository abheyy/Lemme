package com.example.Lemme.Borrow.stuff.controller;

import com.example.Lemme.Borrow.stuff.model.BorrowRequest;
import com.example.Lemme.Borrow.stuff.service.BorrowRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowRequestController {

    private final BorrowRequestService borrowRequestService;

    @PostMapping
    public ResponseEntity<BorrowRequest> createRequest(@RequestBody BorrowRequest request) {
        return ResponseEntity.ok(borrowRequestService.createRequest(request));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<BorrowRequest> approveRequest(@PathVariable String id) {
        return ResponseEntity.ok(borrowRequestService.approveRequest(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<BorrowRequest> rejectRequest(@PathVariable String id) {
        return ResponseEntity.ok(borrowRequestService.rejectRequest(id));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowRequest> markReturned(@PathVariable String id) {
        return ResponseEntity.ok(borrowRequestService.markReturned(id));
    }

    @GetMapping("/borrower/{borrowerId}")
    public ResponseEntity<List<BorrowRequest>> getByBorrower(@PathVariable String borrowerId) {
        return ResponseEntity.ok(borrowRequestService.getRequestsByBorrower(borrowerId));
    }

    @GetMapping("/lender/{lenderId}")
    public ResponseEntity<List<BorrowRequest>> getByLender(@PathVariable String lenderId) {
        return ResponseEntity.ok(borrowRequestService.getRequestsByLender(lenderId));
    }
}
