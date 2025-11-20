package com.eventify.eventify.mapper;

import com.eventify.eventify.dto.UserRequest;
import com.eventify.eventify.dto.UserResponse;
import com.eventify.eventify.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true) // service sets default ROLE_USER
    User toEntity(UserRequest request);

    @Mapping(target = "role", expression = "java(user.getRole().name())")
    UserResponse toResponse(User user);
}
