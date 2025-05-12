package com.stockmanager.stockmanager.service;

import com.stockmanager.stockmanager.dto.AppUserDTO;
import com.stockmanager.stockmanager.model.AppUser;
import com.stockmanager.stockmanager.model.Subscription;
import com.stockmanager.stockmanager.repository.AppUserRepository;
import com.stockmanager.stockmanager.repository.SubscriptionPaymentRepository;
import com.stockmanager.stockmanager.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stockmanager.stockmanager.mapper.AppUserMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppUserService {

    private final SubscriptionRepository subscriptionRepository;
    private final AppUserRepository appUserRepository;
    private final SubscriptionPaymentRepository subscriptionPaymentRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, SubscriptionRepository subscriptionRepository, SubscriptionPaymentRepository subscriptionPaymentRepository) {
        this.appUserRepository = appUserRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionPaymentRepository = subscriptionPaymentRepository;
    }



    public AppUser createAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public AppUserDTO findById(UUID id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);

        if (appUser.isPresent()) {
            return AppUserMapper.toDTO(appUser.get()); // Mapping entity to DTO
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Mise à jour d'un utilisateur
    public AppUserDTO updateAppUser(UUID id, AppUser appUserDetails) {
        Optional<AppUser> existingAppUser = appUserRepository.findById(id);

        if (existingAppUser.isPresent()) {
            AppUser appUser = existingAppUser.get();

            // Mise à jour des attributs de l'utilisateur
            appUser.setFirstName(appUserDetails.getFirstName());
            appUser.setLastName(appUserDetails.getLastName());
            appUser.setBirthDate(appUserDetails.getBirthDate());
            appUser.setEmail(appUserDetails.getEmail());
            appUser.setRole(appUserDetails.getRole());
            appUser.setCountry(appUserDetails.getCountry());
            appUser.setAddress(appUserDetails.getAddress());
            appUser.setAge(appUserDetails.getAge());
            appUser.setPhone(appUserDetails.getPhone());

            // Sauvegarder l'utilisateur mis à jour
            AppUser updatedAppUser = appUserRepository.save(appUser);
            return AppUserMapper.toDTO(updatedAppUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Suppression d'un utilisateur
    @Transactional
    public void deleteAppUser(UUID id) {
        // Supprimer les paiements associés aux abonnements de l'utilisateur
        List<Subscription> subscriptions = subscriptionRepository.findByAppUserId(id);
        for (Subscription subscription : subscriptions) {
            subscriptionPaymentRepository.deleteBySubscriptionId(subscription.getId());
        }

        // Supprimer les abonnements associés à l'utilisateur
        subscriptionRepository.deleteByAppUserId(id);

        // Supprimer l'utilisateur
        appUserRepository.deleteById(id);
    }

    //Add method to get subscriber's full name getFullName()

    public String getFullName(UUID id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isPresent()) {
            return appUser.get().getFirstName() + " " + appUser.get().getLastName();
        }
        throw new RuntimeException("User not found with id: " + id);
    }

    public String getEmail(UUID id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isPresent()) {
            return appUser.get().getEmail();
        }
        throw new RuntimeException("User not found with id: " + id);
    }
}
