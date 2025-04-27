package com.stockmanager.stockmanager.service;

import com.stockmanager.stockmanager.dto.SubscriptionPaymentDTO;
import com.stockmanager.stockmanager.mapper.SubscriptionPaymentMapper;
import com.stockmanager.stockmanager.model.Subscription;
import com.stockmanager.stockmanager.model.SubscriptionPayment;
import com.stockmanager.stockmanager.repository.SubscriptionPaymentRepository;
import com.stockmanager.stockmanager.repository.SubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SubscriptionPaymentService {

    private final SubscriptionPaymentRepository subscriptionPaymentRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionPaymentMapper subscriptionPaymentMapper;

    public SubscriptionPaymentService(SubscriptionPaymentRepository subscriptionPaymentRepository,
                                      SubscriptionRepository subscriptionRepository,
                                      SubscriptionPaymentMapper subscriptionPaymentMapper) {
        this.subscriptionPaymentRepository = subscriptionPaymentRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionPaymentMapper = subscriptionPaymentMapper;
    }

    public SubscriptionPaymentDTO createPayment(SubscriptionPaymentDTO dto) {
        Subscription subscription = subscriptionRepository.findById(dto.getSubscriptionId())
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        SubscriptionPayment payment = subscriptionPaymentMapper.toEntity(dto);
        payment.setSubscription(subscription);

        SubscriptionPayment savedPayment = subscriptionPaymentRepository.save(payment);
        return subscriptionPaymentMapper.toDTO(savedPayment);
    }

    public List<SubscriptionPaymentDTO> getPaymentsBySubscriptionId(UUID subscriptionId) {
        List<SubscriptionPayment> payments = subscriptionPaymentRepository.findBySubscriptionId(subscriptionId);
        return subscriptionPaymentMapper.toDTOList(payments);
    }

    public SubscriptionPaymentDTO getPaymentById(UUID id) {
        SubscriptionPayment payment = subscriptionPaymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));
        return subscriptionPaymentMapper.toDTO(payment);
    }

    public void deletePayment(UUID id) {
        subscriptionPaymentRepository.deleteById(id);
    }
}
