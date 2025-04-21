package com.stockmanager.stockmanager.dto;

import com.stockmanager.stockmanager.enums.Plan;
import com.stockmanager.stockmanager.model.AppUser;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class SubscriptonDTO {


    private UUID id;
    private BigDecimal price;
    private BigDecimal totalPaid = BigDecimal.ZERO; // Default value 0.
    private Plan plan;
    private boolean isActive=true;
    private AppUser appUser;
    private String description;
    private LocalDate expiredAt;
}
