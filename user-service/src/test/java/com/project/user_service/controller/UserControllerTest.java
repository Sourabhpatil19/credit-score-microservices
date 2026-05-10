package com.project.user_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.user_service.dto.UserRequestDto;
import com.project.user_service.dto.UserResponseDto;
import com.project.user_service.security.JwtFilter;
import com.project.user_service.security.JwtUtil;
import com.project.user_service.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired
private MockMvc mockMvc;
    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private JwtFilter jwtFilter;
@MockitoBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void createUser_shouldReturn200()throws Exception{
        UserRequestDto request = new UserRequestDto();
        request.setUsername("Sourabh");
        request.setEmail("test@gmail.com");
        request.setPassword("Password1");

        UserResponseDto response = UserResponseDto.builder()
                .id(1L)
                .username("Sourabh")
                .email("test@gmail.com")
                .role("OFFICER")
                .build();

        when(userService.createUser(request))
                .thenReturn(response);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(request)
                        ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.username")
                        .value("Sourabh"));
    }

}
