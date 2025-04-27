package com.stockmanager.stockmanager.mapper;

import com.stockmanager.stockmanager.dto.SubscriptionPaymentDTO;
import com.stockmanager.stockmanager.model.SubscriptionPayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionPaymentMapper {

    @Mapping(source = "subscription.id", target = "subscriptionId")
    SubscriptionPaymentDTO toDTO(SubscriptionPayment subscriptionPayment);

    @Mapping(source = "subscriptionId", target = "subscription.id")
    SubscriptionPayment toEntity(SubscriptionPaymentDTO subscriptionPaymentDTO);

    List<SubscriptionPaymentDTO> toDTOList(List<SubscriptionPayment> subscriptionPayments);
    List<SubscriptionPayment> toEntityList(List<SubscriptionPaymentDTO> subscriptionPaymentDTOs);

}
