package ru.leonov.profile.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leonov.profile.model.dto.UserDto;
import ru.leonov.profile.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", source = "id")
    UserDto toDto(User user);

    @Mapping(target = "createdDate", expression="java(java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC))")
    @Mapping(target = "updatedDate", expression="java(java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC))")
    User toEntity(UserDto user);
}
