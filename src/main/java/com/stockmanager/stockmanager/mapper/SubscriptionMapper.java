package com.stockmanager.stockmanager.mapper;

import com.stockmanager.stockmanager.dto.SubscriptionDTO;
import com.stockmanager.stockmanager.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    @Mapping(source = "appUser.id", target = "userId")
    @Mapping(target = "price", expression = "java(subscription.getPlan().getPrice())")  // Calcul dynamique du prix
    SubscriptionDTO toDTO(Subscription subscription);

    @Mapping(source = "userId", target = "appUser.id")
    Subscription toEntity(SubscriptionDTO dto);

    // Méthode dédiée à la mise à jour partielle de l'entité à partir du DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appUser", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "expiredAt", ignore = true)  // à ignorer si non modifiable via DTO
    @Mapping(target = "totalPaid", ignore = true)  // à ignorer si non modifiable via DTO
    @Mapping(target = "price", ignore = true)
    void updateEntityFromDTO(SubscriptionDTO dto, @MappingTarget Subscription subscription);

    List<SubscriptionDTO> toDTOList(List<Subscription> subscriptions); //mappage de list (utile pour les requete get findAll par exemple transforme une list Subs en List de SubsDTO
    List<Subscription> toEntityList(List<SubscriptionDTO> dtos);

}
