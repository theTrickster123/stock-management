package com.stockmanager.stockmanager.repository;

import com.stockmanager.stockmanager.model.SubscriptionPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubscriptionPaymentRepository extends JpaRepository<SubscriptionPayment, UUID> {

    //Find subscriptionPayments by Subscrption Id
    List<SubscriptionPayment> findBySubscriptionId(UUID subscriptionId);
}
