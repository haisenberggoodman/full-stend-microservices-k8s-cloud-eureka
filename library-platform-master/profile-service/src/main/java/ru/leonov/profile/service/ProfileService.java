package ru.leonov.profile.service;

import ru.leonov.profile.model.dto.UserDto;

import java.util.UUID;

public interface ProfileService {

    UUID registration(UserDto userDto);

    UserDto profile(UUID userId);

}
