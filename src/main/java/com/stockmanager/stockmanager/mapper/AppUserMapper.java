package com.stockmanager.stockmanager.mapper;

import com.stockmanager.stockmanager.dto.AppUserDTO;
import com.stockmanager.stockmanager.model.AppUser;

public class AppUserMapper {

    public static AppUserDTO toDTO(AppUser appUser) {
        if (appUser == null) {
            return null;
        }
        // UUID id, String firstName, String lastName,LocalDate birthDate, String email, Role role, String country, boolean isActive, boolean isBanned, String address, int age , String phone
        return new AppUserDTO(appUser.getId(),appUser.getFirstName(),appUser.getLastName(),appUser.getBirthDate(),appUser.getEmail(),appUser.getRole(),appUser.getCountry(),appUser.isActive(), appUser.isBanned(),appUser.getAddress(),appUser.getAge(),appUser.getPhone());
    }

    // Optionally: for creating an entity from DTO (e.g., for registration)
    public static AppUser toEntity(AppUserDTO dto) {
        if (dto == null) {
            return null;
        }
        AppUser user = new AppUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBirthDate(dto.getBirthDate());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setCountry(dto.getCountry());
        user.setActive(dto.isActive());
        user.setBanned(dto.isBanned());
        user.setAddress(dto.getAddress());
        user.setAge(dto.getAge());
        user.setPhone(dto.getPhone());
        return user;
    }
}
