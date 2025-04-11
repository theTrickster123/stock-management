package com.stockmanager.stockmanager.repository;

import com.stockmanager.stockmanager.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

}
