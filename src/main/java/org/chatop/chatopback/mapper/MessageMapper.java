package org.chatop.chatopback.mapper;

import org.chatop.chatopback.dto.MessageDto;
import org.chatop.chatopback.entity.Message;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageMapper {

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "rentalId", target = "rental.id")
    Message toEntity(MessageDto messageDto);

    MessageDto toDto(Message message);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Message partialUpdate(MessageDto messageDto, @MappingTarget Message message);
}