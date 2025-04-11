package com.stockmanager.stockmanager.dto;

import com.stockmanager.stockmanager.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class AppUserDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private Role role;
    private String country;
    private boolean isActive;
    private boolean isBanned;
    private String address;
    private int age;
    private String phone;

    // Constructor(s)
    public AppUserDTO() {}



    public AppUserDTO(UUID id, String firstName, String lastName,LocalDate birthDate, String email, Role role, String country, boolean isActive, boolean isBanned, String address, int age , String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.role = role;
        this.country = country;
        this.isActive = isActive;
        this.isBanned = isBanned;
        this.address = address;
        this.age = age;
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //Note: No password, createdAt, or updatedAt in the DTO to protect sensitive/internal info.
}
