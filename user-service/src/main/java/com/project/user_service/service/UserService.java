package com.project.user_service.service;

import com.project.user_service.dto.UserRequestDto;
import com.project.user_service.dto.UserResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService {
  Page<UserResponseDto> getUsers(
          int page,
          int size,
          String sortBy,
          String search
  );
  UserResponseDto createUser(UserRequestDto dto);

  UserResponseDto getUserById(Long id);

  List<UserResponseDto> getAllUsers();

  UserResponseDto updateUser(Long id,UserRequestDto dto);
  void deleteUser(Long id);

}
