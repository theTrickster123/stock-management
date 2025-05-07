package com.stockmanager.stockmanager.controller;

import com.stockmanager.stockmanager.dto.SubscriptionPaymentDTO;
import com.stockmanager.stockmanager.exception.InsufficientPaymentException;
import com.stockmanager.stockmanager.service.SubscriptionPaymentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class SubscriptionPaymentController {

    private final SubscriptionPaymentService subscriptionPaymentService;

    public SubscriptionPaymentController(SubscriptionPaymentService subscriptionPaymentService) {
        this.subscriptionPaymentService = subscriptionPaymentService;
    }

    @PostMapping("/{subscriptionId}")
    public ResponseEntity<?> createPayment(@PathVariable UUID subscriptionId,
                                                                @RequestParam BigDecimal amountPayed) {
        try {
            SubscriptionPaymentDTO subscriptionPaymentDTO = subscriptionPaymentService
                    .createSubscriptionPayment(subscriptionId, amountPayed);
            return ResponseEntity.ok(subscriptionPaymentDTO);
        } catch (InsufficientPaymentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/subscription/{subscriptionId}")
    public ResponseEntity<List<SubscriptionPaymentDTO>> getPaymentsBySubscriptionId(@PathVariable UUID subscriptionId) {
        List<SubscriptionPaymentDTO> payments = subscriptionPaymentService.getPaymentsBySubscriptionId(subscriptionId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionPaymentDTO> getPaymentById(@PathVariable UUID id) {
        SubscriptionPaymentDTO payment = subscriptionPaymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable UUID id) {
        subscriptionPaymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

}
