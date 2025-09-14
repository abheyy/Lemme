package com.example.Lemme.Borrow.stuff.service;




import com.example.Lemme.Borrow.stuff.model.BorrowRequest;
import com.example.Lemme.Borrow.stuff.model.BorrowStatus;
import com.example.Lemme.Borrow.stuff.model.Item;
import com.example.Lemme.Borrow.stuff.repository.BorrowRequestRepository;
import com.example.Lemme.Borrow.stuff.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowRequestService {

    private final BorrowRequestRepository borrowRequestRepository;
    private final ItemRepository itemRepository;

    // Create a borrow request
    public BorrowRequest createRequest(BorrowRequest request) {
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (!item.isAvailable()) {
            throw new RuntimeException("Item is not available");
        }

        request.setStatus(BorrowStatus.PENDING);
        request.setLenderId(item.getOwnerId());

        return borrowRequestRepository.save(request);
    }

    // Approve borrow request
    public BorrowRequest approveRequest(String requestId) {
        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getStatus() != BorrowStatus.PENDING) {
            throw new RuntimeException("Request cannot be approved");
        }

        // Mark item as unavailable
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setAvailable(false);
        itemRepository.save(item);

        request.setStatus(BorrowStatus.APPROVED);
        return borrowRequestRepository.save(request);
    }

    // Reject borrow request
    public BorrowRequest rejectRequest(String requestId) {
        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(BorrowStatus.REJECTED);
        return borrowRequestRepository.save(request);
    }

    // Mark as returned
    public BorrowRequest markReturned(String requestId) {
        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(BorrowStatus.RETURNED);

        // Mark item as available again
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setAvailable(true);
        itemRepository.save(item);

        return borrowRequestRepository.save(request);
    }

    // Get borrow requests by borrower
    public List<BorrowRequest> getRequestsByBorrower(String borrowerId) {
        return borrowRequestRepository.findByBorrowerId(borrowerId);
    }

    // Get borrow requests by lender
    public List<BorrowRequest> getRequestsByLender(String lenderId) {
        return borrowRequestRepository.findByLenderId(lenderId);
    }
}
