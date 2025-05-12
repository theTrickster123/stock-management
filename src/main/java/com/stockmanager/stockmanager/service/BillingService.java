package com.stockmanager.stockmanager.service;

import com.stockmanager.stockmanager.dto.BillResponseDTO;
import com.stockmanager.stockmanager.enums.Plan;
import com.stockmanager.stockmanager.model.AppUser;
import com.stockmanager.stockmanager.model.Subscription;
import com.stockmanager.stockmanager.model.SubscriptionPayment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;
import java.util.UUID;

@Service
public class BillingService {

    public final AppUserService appUserService;
    private final LatexInvoiceService latexInvoiceService;
    private final EmailService emailService;

    public BillingService(AppUserService appUserService, LatexInvoiceService latexInvoiceService, EmailService emailService) {
        this.appUserService = appUserService;
        this.latexInvoiceService = latexInvoiceService;
        this.emailService = emailService;
    }

    public BillResponseDTO buildBillDTO(SubscriptionPayment payment) {
        Subscription subscription = payment.getSubscription();
        UUID userID = subscription.getAppUser().getId();
        Plan plan = subscription.getPlan();

        BillResponseDTO dto = new BillResponseDTO();
        dto.setBrandName("InvyFlow");
        dto.setOwnerFullName("Mehdi Tarissi");
        dto.setOwnerContactEmail("contact@invyflow.com");

        dto.setSubscriberFullName(appUserService.getFullName(userID));
        dto.setPlanName(plan.getPlanName());
        dto.setSubscriberPlanPrice(plan.getPrice());

        dto.setTotalPaid(payment.getAmountPayed());
        dto.setPaymentDate(payment.getCreatedAt());
        dto.setExpiredDate(payment.getCreatedAt().plusDays(30));
        dto.setSubscriberContactEmail(appUserService.getFullName(userID));


        return dto;
    }

    public void generateAndSendInvoice(SubscriptionPayment payment) throws Exception {
        BillResponseDTO bill = buildBillDTO(payment);

        Map<String, String> placeholders = Map.of(
                "brandName", bill.getBrandName(),
                "ownerFullName", bill.getOwnerFullName(),
                "subscriberFullName", bill.getSubscriberFullName(),
                "subscriberPlanPrice", bill.getSubscriberPlanPrice().toPlainString(),
                "totalPaid", bill.getTotalPaid().toPlainString(),
                "expiredDate", bill.getExpiredDate().toString(),
                "ownerContactEmail", bill.getOwnerContactEmail(),
                "paymentDate", bill.getPaymentDate().toString(),
                "planName", bill.getPlanName()
        );

        String billId = UUID.randomUUID().toString();
        File tex = latexInvoiceService.generateLatexFile(billId, placeholders);
        File pdf = latexInvoiceService.compileToPdf(tex);

        String recipientEmail = payment.getSubscription().getAppUser().getEmail();
        emailService.sendBill(recipientEmail, pdf);
    }

}
