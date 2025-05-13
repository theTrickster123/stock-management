package com.stockmanager.stockmanager.controller;

import com.stockmanager.stockmanager.dto.AppUserDTO;

import com.stockmanager.stockmanager.model.AppUser;
import com.stockmanager.stockmanager.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public AppUser createAppUser(@RequestBody AppUser appUser) {
        return appUserService.createAppUser(appUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getAppUserById(@PathVariable UUID id) {
        try {
            AppUserDTO appUserDTO = appUserService.findById(id);
            return new ResponseEntity<>(appUserDTO, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Not Found if user doesn't exist
        }
    }

    // Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<AppUserDTO> updateAppUser(@PathVariable UUID id, @RequestBody AppUser appUser) {
        AppUserDTO updatedAppUser = appUserService.updateAppUser(id, appUser);
        return ResponseEntity.ok(updatedAppUser);
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppUser(@PathVariable UUID id) {
        appUserService.deleteAppUser(id);
        return ResponseEntity.noContent().build();
    }

}

