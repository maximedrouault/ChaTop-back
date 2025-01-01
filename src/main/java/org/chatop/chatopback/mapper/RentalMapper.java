package org.chatop.chatopback.mapper;

import org.chatop.chatopback.dto.RentalDto;
import org.chatop.chatopback.entity.Rental;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RentalMapper {

    Rental toEntity(RentalDto rentalDto);

    @Mapping(source = "owner.id", target = "ownerId")
    RentalDto toDto(Rental rental);

    @Mapping(source = "rental.owner.id", target = "ownerId")
    @Mapping(source = "signedPictureUrl", target = "picture")
    RentalDto toDto(Rental rental, String signedPictureUrl);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Rental partialUpdate(RentalDto rentalDto, @MappingTarget Rental rental);
}