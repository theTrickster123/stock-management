package com.stockmanager.stockmanager.mapper;

import com.stockmanager.stockmanager.dto.SubscriptionDTO;
import com.stockmanager.stockmanager.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    @Mapping(source = "appUser.id", target = "userId")
    SubscriptionDTO toDTO(Subscription subscription);

    @Mapping(source = "userId", target = "appUser.id")
    Subscription toEntity(SubscriptionDTO dto);

    List<SubscriptionDTO> toDTOList(List<Subscription> subscriptions); //mappage de list (utile pour les requete get findAll par exemple transforme une list Subs en List de SubsDTO
    List<Subscription> toEntityList(List<SubscriptionDTO> dtos);

}
