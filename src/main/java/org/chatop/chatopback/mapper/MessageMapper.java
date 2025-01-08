package org.chatop.chatopback.mapper;

import org.chatop.chatopback.dto.message.MessageRequestDto;
import org.chatop.chatopback.entity.Message;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageMapper {

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "rentalId", target = "rental.id")
    Message toEntity(MessageRequestDto messageRequestDto);

    MessageRequestDto toDto(Message message);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Message partialUpdate(MessageRequestDto messageRequestDto, @MappingTarget Message message);
}