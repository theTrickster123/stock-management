package com.stockmanager.stockmanager.repository;

import com.stockmanager.stockmanager.dto.SubscriptionDTO;
import com.stockmanager.stockmanager.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    List<Subscription> findByAppUserId(UUID userId);

}
