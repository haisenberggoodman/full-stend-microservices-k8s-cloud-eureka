package ru.leonov.profile.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record UserDto(UUID userId,
                      @NotBlank
                      String name,
                      @Email(message = "Invalid email")
                      String email,
                      @Pattern(regexp = PHONE_FORMATTER, message = "Invalid phone number")
                      String phone,

                      @PositiveOrZero
                      BigDecimal amount) {

    private static final String PHONE_FORMATTER = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";

}
