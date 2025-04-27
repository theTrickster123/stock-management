package com.stockmanager.stockmanager.service;

import com.stockmanager.stockmanager.dto.SubscriptionDTO;
import com.stockmanager.stockmanager.mapper.SubscriptionMapper;
import com.stockmanager.stockmanager.model.Subscription;
import com.stockmanager.stockmanager.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
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

    //Supprimer une souscription
    public void deleteSubscription(UUID id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        subscriptionRepository.delete(subscription);
    }
    //Update susbscription
    public SubscriptionDTO updateSubscription(SubscriptionDTO subscriptionDTO, UUID id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        if (subscription.isEmpty()) {
            throw new RuntimeException("Subscription not found");  // Gérer l'exception
        }




        // Mapper DTO -> Entity et mettre à jour
        Subscription existingSubscription = subscription.get();
        existingSubscription = subscriptionMapper.toEntity(subscriptionDTO);  // Mettre à jour les valeurs avec la methode toEntity de l'interface SubscriptionMapper
        existingSubscription.setId(id);  // Garder le même ID

        //ne pas avoir null sur expired_at
        existingSubscription.setExpiredAt(
                existingSubscription.getExpiredAt() != null
                        ? existingSubscription.getExpiredAt()
                        : subscription.get().getExpiredAt()
        );
        existingSubscription = subscriptionRepository.save(existingSubscription);  // Sauvegarder dans la DB

        return subscriptionMapper.toDTO(existingSubscription);  // Retourner le DTO de la souscription mise à jour

        }
    }



