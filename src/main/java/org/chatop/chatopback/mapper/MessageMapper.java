package org.chatop.chatopback.mapper;

import org.chatop.chatopback.dto.message.MessageRequestDto;
import org.chatop.chatopback.entity.Message;
import org.mapstruct.*;

/**
 * Mapper interface for converting MessageRequestDto to Message entity.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageMapper {

    /**
     * Converts a MessageRequestDto to a Message entity.
     *
     * @param messageRequestDto the DTO to convert
     * @return the converted Message entity
     */
    @Mapping(source = "rentalId", target = "rental.id")
    Message toEntity(MessageRequestDto messageRequestDto);
}