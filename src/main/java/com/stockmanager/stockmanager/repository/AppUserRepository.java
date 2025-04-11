package com.stockmanager.stockmanager.repository;

import com.stockmanager.stockmanager.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
}
