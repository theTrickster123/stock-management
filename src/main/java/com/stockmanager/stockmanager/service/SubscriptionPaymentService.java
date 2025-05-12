package com.stockmanager.stockmanager.service;

import com.stockmanager.stockmanager.dto.BillResponseDTO;
import com.stockmanager.stockmanager.dto.SubscriptionPaymentDTO;
import com.stockmanager.stockmanager.exception.InsufficientPaymentException;
import com.stockmanager.stockmanager.mapper.SubscriptionPaymentMapper;
import com.stockmanager.stockmanager.model.Subscription;
import com.stockmanager.stockmanager.model.SubscriptionPayment;
import com.stockmanager.stockmanager.repository.SubscriptionPaymentRepository;
import com.stockmanager.stockmanager.repository.SubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class SubscriptionPaymentService {

    private final SubscriptionPaymentRepository subscriptionPaymentRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionPaymentMapper subscriptionPaymentMapper;
    private final BillingService billingService;


    public SubscriptionPaymentService(SubscriptionPaymentRepository subscriptionPaymentRepository,
                                      SubscriptionRepository subscriptionRepository,
                                      SubscriptionPaymentMapper subscriptionPaymentMapper, LatexInvoiceService latexInvoiceService, EmailService emailService, BillingService billingService) {
        this.subscriptionPaymentRepository = subscriptionPaymentRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionPaymentMapper = subscriptionPaymentMapper;
        this.billingService = billingService;
    }

    public SubscriptionPaymentDTO createSubscriptionPayment(UUID subscriptionId, BigDecimal amountPayed) throws Exception {
        // Récupérer la souscription associée
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        // Créer un nouvel enregistrement de paiement
        SubscriptionPayment subscriptionPayment = new SubscriptionPayment();
        subscriptionPayment.setAmountPayed(amountPayed);
        subscriptionPayment.setSubscription(subscription);


        // Mettre à jour la date d'expiration et l'état de l'abonnement
        updateSubscriptionAfterPayment(subscriptionId, amountPayed);

        // Mapper en DTO et retourner
        return subscriptionPaymentMapper.toDTO(subscriptionPayment);
    }

    // Méthode pour mettre à jour l'abonnement après paiement (a tester)
    public void updateSubscriptionAfterPayment(UUID id, BigDecimal amountPayed) throws Exception {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        //Si l'abo est toujours actif pas besoin de payer
        if(subscription.isActive()) {
            subscriptionRepository.save(subscription);
        }
        else {
            // Ajouter le montant payé au total
            subscription.setTotalPaid(subscription.getTotalPaid().add(amountPayed));

            // Vérifier si le montant payé atteint ou dépasse le prix
            if (amountPayed.compareTo(subscription.getPrice()) >= 0) {

                // Créer un nouvel enregistrement de paiement
                SubscriptionPayment subscriptionPayment = new SubscriptionPayment();
                subscriptionPayment.setAmountPayed(amountPayed);
                subscriptionPayment.setSubscription(subscription);
                subscriptionPaymentRepository.save(subscriptionPayment);

                // Mettre à jour la date d'expiration de l'abonnement
                subscription.setExpiredAt(LocalDate.now().plusMonths(1));  // Exemple : ajouter un mois à l'expiration

                // L'abonnement devient actif
                subscription.setActive(true);
                billingService.generateAndSendInvoice(subscriptionPayment);
            } else {
                // Si le paiement est insuffisant, désactiver l'abonnement
                throw new InsufficientPaymentException("Montant insuffisant");

            }

            // Sauvegarder l'abonnement mis à jour
            subscriptionRepository.save(subscription);
        }
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
