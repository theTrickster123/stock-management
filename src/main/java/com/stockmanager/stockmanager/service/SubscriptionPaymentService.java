package com.stockmanager.stockmanager.service;

import com.stockmanager.stockmanager.model.SubscriptionPayment;
import com.stockmanager.stockmanager.repository.SubscriptionPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionPaymentService {

    private final SubscriptionPaymentRepository subscriptionPaymentRepository;

    @Autowired
    public SubscriptionPaymentService(SubscriptionPaymentRepository subscriptionPaymentRepository) {
        this.subscriptionPaymentRepository = subscriptionPaymentRepository;
    }


    public SubscriptionPayment createSubscriptionPayment(SubscriptionPayment subscriptionPayment) {
        return subscriptionPaymentRepository.save(subscriptionPayment);
    }
}
