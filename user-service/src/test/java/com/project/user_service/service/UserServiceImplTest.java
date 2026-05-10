package com.project.user_service.service;

import com.project.user_service.dto.UserRequestDto;
import com.project.user_service.dto.UserResponseDto;
import com.project.user_service.entity.User;
import com.project.user_service.enums.Role;
import com.project.user_service.expection.DuplicateResourceException;
import com.project.user_service.expection.ResourceNotFoundException;
import com.project.user_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser_ShouldCreateSuccessfully(){
        UserRequestDto dto= new UserRequestDto();
        dto.setUsername("Sourabh");
        dto.setEmail("test@gmail.com");
        dto.setPassword("Password1");
        User savedUser=User.builder().id(1L)
                .username("Sourabh")
                .email("test@gmail.com")
                .password("Password1")
                .role(Role.OFFICER)
                .build();

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(dto.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        UserResponseDto responseDto= userService.createUser(dto);

        assertNotNull(responseDto);
        assertEquals("Sourabh",responseDto.getUsername());
        assertEquals(
                "test@gmail.com",
                responseDto.getEmail()
        );

        verify(userRepository, times(1))
                .save(any(User.class));

    }
@Test
    void createUser_ShouldThrowDuplicateException(){
        UserRequestDto dto= new UserRequestDto();
    dto.setEmail("test@gmail.com");

    User existingUser = User.builder()
            .email("test@gmail.com")
            .build();

    when(userRepository.findByEmail(dto.getEmail()))
            .thenReturn(Optional.of(existingUser));

    assertThrows(
            DuplicateResourceException.class,
            () -> userService.createUser(dto)
    );

    verify(userRepository, never())
            .save(any(User.class));
}

    @Test
    void getUserById_ShouldReturnUser() {

        User user = User.builder()
                .id(1L)
                .username("Sourabh")
                .email("test@gmail.com")
                .role(Role.OFFICER)
                .build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        UserResponseDto response =
                userService.getUserById(1L);

        assertEquals(
                "Sourabh",
                response.getUsername()
        );
    }

    @Test
    void getUserById_ShouldThrowException() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getUserById(1L)
        );
    }
}

