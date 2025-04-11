package com.stockmanager.stockmanager.model;

import com.stockmanager.stockmanager.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 32)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 32)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "email", nullable = false, unique = true, length = 64)
    private String email;

    @Column(nullable = false, length = 128) //hash password
    private String password;

    @Column(nullable = false,length = 15)
    private Role role;

    @Column(nullable = false)
    private String country="Morocco";

    @Column(name="is_active",nullable = false)
    private boolean isActive=true;

    @Column(name="is_banned", nullable = false)
    private boolean isBanned=false;

    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @Column(length = 255)
    private String address;

    @Column(length=2)
    private int age;

    @Column(nullable = false,length = 20)
    private String phone;

    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    //add image field nullable true for user

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public AppUser(UUID id, String firstName, String lastName, LocalDate birthDate, String email, String password, Role role, String country, boolean isActive, boolean isBanned, LocalDateTime updatedAt, String address, int age, String phone, LocalDateTime createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.role = role;
        this.country = country;
        this.isActive = isActive;
        this.isBanned = isBanned;
        this.updatedAt = updatedAt;
        this.address = address;
        this.age = age;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public AppUser() {

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", country='" + country + '\'' +
                ", isActive=" + isActive +
                ", isBanned=" + isBanned +
                ", updatedAt=" + updatedAt +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return isActive == appUser.isActive && isBanned == appUser.isBanned && age == appUser.age && Objects.equals(id, appUser.id) && Objects.equals(firstName, appUser.firstName) && Objects.equals(lastName, appUser.lastName) && Objects.equals(birthDate, appUser.birthDate) && Objects.equals(email, appUser.email) && Objects.equals(password, appUser.password) && role == appUser.role && Objects.equals(country, appUser.country) && Objects.equals(updatedAt, appUser.updatedAt) && Objects.equals(address, appUser.address) && Objects.equals(phone, appUser.phone) && Objects.equals(createdAt, appUser.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, email, password, role, country, isActive, isBanned, updatedAt, address, age, phone, createdAt);
    }
}
