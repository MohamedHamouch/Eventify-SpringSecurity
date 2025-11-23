package com.eventify.eventify.mapper;

import com.eventify.eventify.dto.RegistrationResponse;
import com.eventify.eventify.entity.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    @Mapping(target = "userId", expression = "java(registration.getUser() != null ? registration.getUser().getId() : null)")
    @Mapping(target = "eventId", expression = "java(registration.getEvent() != null ? registration.getEvent().getId() : null)")
    @Mapping(target = "status", expression = "java(registration.getStatus() != null ? registration.getStatus().name() : null)")
    RegistrationResponse toResponse(Registration registration);
}
