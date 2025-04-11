package com.stockmanager.stockmanager.repository;

import com.stockmanager.stockmanager.model.SubscriptionPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPaymentRepository extends JpaRepository<SubscriptionPayment, Integer> {
}
