package com.stockmanager.stockmanager.service;

import com.stockmanager.stockmanager.dto.AppUserDTO;
import com.stockmanager.stockmanager.model.AppUser;
import com.stockmanager.stockmanager.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stockmanager.stockmanager.mapper.AppUserMapper;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
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
}
