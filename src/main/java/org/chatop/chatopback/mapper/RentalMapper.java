package org.chatop.chatopback.mapper;

import org.chatop.chatopback.dto.CreateRentalRequestDto;
import org.chatop.chatopback.dto.RentalResponseDto;
import org.chatop.chatopback.entity.Rental;
import org.mapstruct.*;

import java.net.URL;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RentalMapper {

    @Mapping(source = "ownerId", target = "owner.id", defaultValue = "1") // TODO: remove default value when authentication is implemented
    @Mapping(target = "picture", ignore = true)
    Rental toEntity(CreateRentalRequestDto createRentalRequestDto);

    @Mapping(source = "owner.id", target = "ownerId")
    RentalResponseDto toDto(Rental rental);

    @Mapping(source = "rental.owner.id", target = "ownerId")
    @Mapping(source = "signedPictureUrl", target = "picture")
    RentalResponseDto toDto(Rental rental, URL signedPictureUrl);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Rental partialUpdate(RentalResponseDto rentalResponseDto, @MappingTarget Rental rental);
}