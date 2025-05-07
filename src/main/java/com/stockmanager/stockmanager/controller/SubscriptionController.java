package com.stockmanager.stockmanager.controller;

import com.stockmanager.stockmanager.dto.SubscriptionDTO;
import com.stockmanager.stockmanager.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/subs")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping()
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        subscriptionService.createSubscription(subscriptionDTO);
        return new ResponseEntity<>(subscriptionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/all-subs/{userId}")
    public ResponseEntity<List<SubscriptionDTO>> getAllSubscriptionByUserId(@PathVariable UUID userId) {
        List<SubscriptionDTO> allUserSubs = subscriptionService.getAllSubscriptionByUserId(userId);
        return new ResponseEntity<>(allUserSubs, HttpStatus.OK);
    }

    @DeleteMapping("/delete-sub/{subscriptionId}")
    public ResponseEntity<SubscriptionDTO> deleteSubscription(@PathVariable UUID subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> updateSubscription(
            @PathVariable UUID id,
            @Valid @RequestBody SubscriptionDTO subscriptionDTO) {

        SubscriptionDTO updated = subscriptionService.updateSubscription(subscriptionDTO, id);
        return ResponseEntity.ok(updated);
    }

    /*@PostMapping("/updateStatus")
    public ResponseEntity<String> updateSubscriptionStatus() {
        // Appeler la méthode de mise à jour des statuts
        subscriptionService.updateSubscriptionStatus();
        return ResponseEntity.ok("Subscription status updated successfully.");
    }*/
}
