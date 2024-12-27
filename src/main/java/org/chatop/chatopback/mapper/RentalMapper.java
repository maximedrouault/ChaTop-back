package org.chatop.chatopback.mapper;

import org.chatop.chatopback.dto.RentalDto;
import org.chatop.chatopback.entity.Rental;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RentalMapper {

    @Mapping(source = "ownerId", target = "owner.id")
    Rental toEntity(RentalDto rentalDto);

    @Mapping(source = "owner.id", target = "ownerId")
    RentalDto toDto(Rental rental);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Rental partialUpdate(RentalDto rentalDto, @MappingTarget Rental rental);
}