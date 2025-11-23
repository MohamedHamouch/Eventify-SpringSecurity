package com.eventify.eventify.mapper;

import com.eventify.eventify.dto.EventRequest;
import com.eventify.eventify.dto.EventResponse;
import com.eventify.eventify.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organizer", ignore = true) // set in service from auth principal
    Event toEntity(EventRequest request);

    @Mapping(target = "organizerId", expression = "java(event.getOrganizer() != null ? event.getOrganizer().getId() : null)")
    EventResponse toResponse(Event event);

    @Mapping(target = "organizer", ignore = true) // do not change organizer via update
    void updateEntity(EventRequest request, @MappingTarget Event event);
}
