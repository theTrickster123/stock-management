package com.stockmanager.stockmanager.service;

import com.stockmanager.stockmanager.dto.SubscriptionDTO;
import com.stockmanager.stockmanager.mapper.SubscriptionMapper;
import com.stockmanager.stockmanager.model.Subscription;
import com.stockmanager.stockmanager.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    // Récupérer toutes les souscriptions d'un utilisateur
    @Transactional //Have to check why I had to add Transactional
    public List<SubscriptionDTO> getAllSubscriptionByUserId(UUID userId) {
        List<Subscription> subscriptions = subscriptionRepository.findByAppUserId(userId);
        return subscriptionMapper.toDTOList(subscriptions);

    }

    //Créer une nouvelle souscription
    public SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionMapper.toEntity(subscriptionDTO);
        subscription = subscriptionRepository.save(subscription);
        return subscriptionMapper.toDTO(subscription);

    }


}
