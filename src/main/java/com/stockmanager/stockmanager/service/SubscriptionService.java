package com.stockmanager.stockmanager.service;

import com.stockmanager.stockmanager.dto.SubscriptionDTO;
import com.stockmanager.stockmanager.mapper.SubscriptionMapper;
import com.stockmanager.stockmanager.model.Subscription;
import com.stockmanager.stockmanager.model.SubscriptionPayment;
import com.stockmanager.stockmanager.repository.SubscriptionPaymentRepository;
import com.stockmanager.stockmanager.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubscriptionService {
    private final SubscriptionPaymentRepository subscriptionPaymentRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper,SubscriptionPaymentRepository subscriptionPaymentRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
        this.subscriptionPaymentRepository = subscriptionPaymentRepository;
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
        // Initialise expired_at à la date actuelle + 1 mois
        LocalDate currentDate = LocalDate.now();
        LocalDate expiredAt = currentDate.plusMonths(1); // 1 mois par défaut
        subscription.setExpiredAt(expiredAt);

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

    @Scheduled(cron = "0 */10 * * * *") // Exécuté toutes les 24 heures (a test)
    public void updateSubscriptionStatus() {
        LocalDate currentDate = LocalDate.now();
        List<Subscription> subscriptions = subscriptionRepository.findAll();

        for (Subscription subscription : subscriptions) {
            Optional<SubscriptionPayment> lastPaymentOpt = subscriptionPaymentRepository
                    .findFirstBySubscriptionOrderByCreatedAtDesc(subscription);

            boolean isActive = false;

            if (lastPaymentOpt.isPresent()) {
                SubscriptionPayment lastPayment = lastPaymentOpt.get();
                LocalDate newExpiredAt = lastPayment.getCreatedAt().toLocalDate().plusMonths(1);

                // Mise à jour de la date d'expiration si elle a changé
                if (!newExpiredAt.equals(subscription.getExpiredAt())) {
                    subscription.setExpiredAt(newExpiredAt);
                }

                // Vérifie si l'abonnement est encore valide
                isActive = !currentDate.isAfter(newExpiredAt);
            } else {
                // Aucun paiement : expiration inchangée, mais on vérifie quand même si elle est dépassée
                isActive = subscription.getExpiredAt().isAfter(currentDate);
            }

            // Mise à jour du statut uniquement si nécessaire
            if (subscription.isActive() != isActive) {
                subscription.setActive(isActive);
            }

            subscriptionRepository.save(subscription);
        }
    }




}



