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

import java.math.BigDecimal;
import java.time.LocalDate;
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

    /*public SubscriptionPaymentDTO createPayment(SubscriptionPaymentDTO dto) {
        Subscription subscription = subscriptionRepository.findById(dto.getSubscriptionId())
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        SubscriptionPayment payment = subscriptionPaymentMapper.toEntity(dto);
        payment.setSubscription(subscription);

        SubscriptionPayment savedPayment = subscriptionPaymentRepository.save(payment);
        return subscriptionPaymentMapper.toDTO(savedPayment);
    }*/

    public SubscriptionPaymentDTO createSubscriptionPayment(UUID subscriptionId, BigDecimal amountPayed) {
        // Récupérer la souscription associée
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        // Créer un nouvel enregistrement de paiement
        SubscriptionPayment subscriptionPayment = new SubscriptionPayment();
        subscriptionPayment.setAmountPayed(amountPayed);
        subscriptionPayment.setSubscription(subscription);

        // Sauvegarder le paiement
        subscriptionPayment = subscriptionPaymentRepository.save(subscriptionPayment);

        // Mettre à jour la date d'expiration et l'état de l'abonnement
        updateSubscriptionAfterPayment(subscription, amountPayed);

        // Mapper en DTO et retourner
        return subscriptionPaymentMapper.toDTO(subscriptionPayment);
    }

    // Méthode pour mettre à jour l'abonnement après paiement (a tester)
    private void updateSubscriptionAfterPayment(Subscription subscription, BigDecimal amountPayed) {
        // Ajouter le montant payé au total
        subscription.setTotalPaid(subscription.getTotalPaid().add(amountPayed));

        // Vérifier si le montant payé atteint ou dépasse le prix
        if (subscription.getTotalPaid().compareTo(subscription.getPrice()) >= 0) {
            // Mettre à jour la date d'expiration de l'abonnement
            subscription.setExpiredAt(LocalDate.now().plusMonths(1));  // Exemple : ajouter un mois à l'expiration

            // L'abonnement devient actif
            subscription.setActive(true);
        } else {
            // Si le paiement est insuffisant, désactiver l'abonnement
            subscription.setActive(false);
        }

        // Sauvegarder l'abonnement mis à jour
        subscriptionRepository.save(subscription);
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
