package com.project.user_service.controller;

import com.project.user_service.common.ApiResponse;
import com.project.user_service.dto.UserRequestDto;
import com.project.user_service.dto.UserResponseDto;
import com.project.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    //Create User
@PostMapping
    public ResponseEntity<ApiResponse<UserResponseDto>>createUser(@RequestBody UserRequestDto dto){
        return ResponseEntity.ok( new ApiResponse<>(true,"User Created",userService.createUser(dto)));
    }
    //Get User
@GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>>getUser(@PathVariable Long id){
    return ResponseEntity.ok(new ApiResponse<>(true,"User fetched",userService.getUserById(id)));
}
@GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>>getAllUser(){
        return ResponseEntity.ok(new ApiResponse<>(true,"User fetched",userService.getAllUsers()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>>updateUser(@PathVariable Long id,@RequestBody UserRequestDto dto){
    return ResponseEntity.ok(new ApiResponse<>(true,"User Updated",userService.updateUser(id,dto)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>>deleteUser(@PathVariable Long id ){
    userService.deleteUser(id);
    return ResponseEntity.ok(new ApiResponse<>(true,"User Deleted",null));
    }

}
