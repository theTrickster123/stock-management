package com.stockmanager.stockmanager.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BillResponseDTO {

    String brandName="InvyFlow"; //InvyFlow
    String ownerFullName="Mehdi Tarissi";
    String subscriberFullName;
    LocalDateTime paymentDate;
    String planName;
    BigDecimal subscriberPlanPrice;
    LocalDateTime expiredDate;
    BigDecimal totalPaid;
    String ownerContactEmail="tarissi.mehdi2019@gmail.com";
    String subscriberContactEmail;

    public BillResponseDTO(String brandName, String ownerFullName, String subscriberFullName, LocalDateTime paymentDate, String planName, BigDecimal subscriberPlanPrice, LocalDateTime expiredDate, BigDecimal totalPaid, String ownerContactEmail, String subscriberContactEmail) {
        this.brandName = brandName;
        this.ownerFullName = ownerFullName;
        this.subscriberFullName = subscriberFullName;
        this.paymentDate = paymentDate;
        this.planName = planName;
        this.subscriberPlanPrice = subscriberPlanPrice;
        this.expiredDate = expiredDate;
        this.totalPaid = totalPaid;
        this.ownerContactEmail = ownerContactEmail;
        this.subscriberContactEmail = subscriberContactEmail;
    }

    public BillResponseDTO() {
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    public String getSubscriberFullName() {
        return subscriberFullName;
    }

    public void setSubscriberFullName(String subscriberFullName) {
        this.subscriberFullName = subscriberFullName;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public BigDecimal getSubscriberPlanPrice() {
        return subscriberPlanPrice;
    }

    public void setSubscriberPlanPrice(BigDecimal subscriberPlanPrice) {
        this.subscriberPlanPrice = subscriberPlanPrice;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }


    public String getOwnerContactEmail() {
        return ownerContactEmail;
    }

    public void setOwnerContactEmail(String ownerContactEmail) {
        this.ownerContactEmail = ownerContactEmail;
    }

    public String getSubscriberContactEmail() {
        return subscriberContactEmail;
    }

    public void setSubscriberContactEmail(String subscriberContactEmail) {
        this.subscriberContactEmail = subscriberContactEmail;
    }
}
