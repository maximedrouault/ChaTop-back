package org.chatop.chatopback.mapper;

import org.chatop.chatopback.dto.rental.CreateRentalRequestDto;
import org.chatop.chatopback.dto.rental.RentalResponseDto;
import org.chatop.chatopback.dto.rental.UpdateRentalRequestDto;
import org.chatop.chatopback.entity.Rental;
import org.mapstruct.*;

import java.net.URL;

/**
 * Mapper interface for converting between Rental entities and DTOs.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RentalMapper {

    /**
     * Converts a CreateRentalRequestDto to a Rental entity.
     *
     * @param createRentalRequestDto the DTO to convert
     * @return the converted Rental entity
     */
    @Mapping(target = "picture", ignore = true)
    Rental toEntity(CreateRentalRequestDto createRentalRequestDto);

    /**
     * Converts a Rental entity to a RentalResponseDto with a signed picture URL.
     *
     * @param rental the entity to convert
     * @param signedPictureUrl the signed URL of the picture
     * @return the converted RentalResponseDto
     */
    @Mapping(source = "rental.owner.id", target = "ownerId")
    @Mapping(source = "signedPictureUrl", target = "picture")
    RentalResponseDto toDto(Rental rental, URL signedPictureUrl);

    /**
     * Partially updates a Rental entity with values from an UpdateRentalRequestDto.
     * Null values in the DTO will be ignored.
     *
     * @param updateRentalRequestDto the DTO with updated values
     * @param rental the entity to update
     * @return the updated Rental entity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Rental partialUpdate(UpdateRentalRequestDto updateRentalRequestDto, @MappingTarget Rental rental);
}