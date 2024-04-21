package ru.leonov.profile.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.leonov.profile.model.dto.UserDto;
import ru.leonov.profile.model.entity.User;
import ru.leonov.profile.model.exception.UserNotFoundException;
import ru.leonov.profile.model.mapper.UserMapperImpl;
import ru.leonov.profile.repository.UserRepository;
import ru.leonov.profile.service.impl.ProfileServiceImpl;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static java.time.ZoneOffset.UTC;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {
        ProfileServiceImpl.class,
        UserMapperImpl.class
})
public class ProfileServiceTest {
    @Autowired
    private ProfileService profileService;
    @MockBean
    private UserRepository userRepository;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setup() {
        var now = ZonedDateTime.now(UTC);
        user = new User(
                UUID.randomUUID(),
                now,
                now,
                "service-test",
                "service-test",
                "Test user",
                BigDecimal.TEN,
                "test@test.test",
                "+77777777777"
        );
        userDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                BigDecimal.TEN
        );
    }

    @Test
    void profile() {
        when(userRepository.findById(eq(user.getId()))).thenReturn(Optional.of(user));
        var actualUser = profileService.profile(user.getId());

        Assertions.assertEquals(userDto, actualUser);

    }
    @Test
    void profile_notFound() {
        Assertions.assertThrows(UserNotFoundException.class, () -> profileService.profile(UUID.randomUUID()));
    }

    @Test
    void registration() {

        when(userRepository.save(any())).thenReturn(user);

        var result = profileService.registration(userDto);

        verify(userRepository, times(1)).save(
                argThat(dto-> dto.getId() == null && user.getName().equals(dto.getName()))
        );

        Assertions.assertEquals(user.getId(), result);

    }

}
