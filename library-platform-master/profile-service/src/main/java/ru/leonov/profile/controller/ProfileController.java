package ru.leonov.profile.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leonov.profile.model.dto.IdDto;
import ru.leonov.profile.model.dto.UserDto;
import ru.leonov.profile.service.ProfileService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public List<UserDto> users() {
        return List.of();
    }

    @PostMapping("/create")
    public ResponseEntity<IdDto> registration(@Valid @RequestBody UserDto userDto) {
        var id = profileService.registration(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new IdDto(id));
    }

    @GetMapping("/{userId}")
    public UserDto profile(@PathVariable("userId") UUID userId) {
        return profileService.profile(userId);
    }
}
