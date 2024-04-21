package ru.leonov.profile.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.leonov.profile.repository.UserRepository;
import ru.leonov.profile.model.dto.UserDto;
import ru.leonov.profile.model.exception.UserNotFoundException;
import ru.leonov.profile.model.mapper.UserMapper;
import ru.leonov.profile.service.ProfileService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UUID registration(UserDto userDto) {
        var entity = userMapper.toEntity(userDto);
        return userRepository.save(entity).getId();
    }

    @Cacheable(value = "user", key = "#userId")
    @Override
    public UserDto profile(UUID userId) {
        var entity = userRepository.findById(userId);
        if (entity.isEmpty()) {
            throw new UserNotFoundException(String.format("Not found user by id=%s", userId));
        }

        return userMapper.toDto(entity.get());
    }
}
