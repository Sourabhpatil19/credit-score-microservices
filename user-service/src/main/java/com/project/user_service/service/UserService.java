package com.project.user_service.service;

import com.project.user_service.dto.UserRequestDto;
import com.project.user_service.dto.UserResponseDto;

import java.util.List;


public interface UserService {

  UserResponseDto createUser(UserRequestDto dto);

  UserResponseDto getUserById(Long id);

  List<UserResponseDto> getAllUsers();

  UserResponseDto updateUser(Long id,UserRequestDto dto);
  void deleteUser(Long id);

}
