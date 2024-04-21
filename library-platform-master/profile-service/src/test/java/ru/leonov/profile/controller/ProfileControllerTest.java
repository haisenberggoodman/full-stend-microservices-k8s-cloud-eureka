package ru.leonov.profile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.leonov.profile.model.dto.IdDto;
import ru.leonov.profile.model.dto.UserDto;
import ru.leonov.profile.service.ProfileService;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ProfileController.class)
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProfileService profileService;

    private UserDto user;

    @BeforeEach
    void setup() {
        user = new UserDto(
                UUID.randomUUID(),
                "Test user",
                "test@test.test",
                "+77777777777",
                BigDecimal.TEN
        );
    }

    @Test
    void profileCreate_thenReturns200() throws Exception {
        when(profileService.registration(eq(user))).thenReturn(user.userId());

        var result = mockMvc.perform(post("/profiles/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var expected = objectMapper.writeValueAsString(new IdDto(user.userId()));

        Assertions.assertEquals(expected, result);
    }

    @Test
    void profileCreate_thenReturns400() throws Exception {
        when(profileService.registration(eq(user))).thenReturn(user.userId());

        var wrongUser = new UserDto(user.userId(), user.name(), "wrong-email.ru", "wrong-phone", null);

        var phoneError = "Invalid phone number";
        var emailError = "Invalid email";

        var error = mockMvc.perform(post("/profiles/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wrongUser)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResolvedException()
                .getMessage();

        Assertions.assertTrue(error.contains(phoneError) && error.contains(emailError));
    }

    @Test
    void profileGet_thenReturns200() throws Exception {
        when(profileService.profile(eq(user.userId()))).thenReturn(user);

        var result = mockMvc.perform(get("/profiles/" + user.userId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertEquals(user, objectMapper.readValue(result, UserDto.class));
    }


}
