package com.project.user_service.service;

import com.project.user_service.dto.UserRequestDto;
import com.project.user_service.dto.UserResponseDto;
import com.project.user_service.entity.User;
import com.project.user_service.enums.Role;
import com.project.user_service.expection.DuplicateResourceException;
import com.project.user_service.expection.ResourceNotFoundException;
import com.project.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService{

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserResponseDto> getUsers(int page, int size, String sortBy, String search) {
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy).ascending());
        Page<User>users;
        if(search!=null && search.isBlank()){
            users=repository.findByEmailContainingIgnoreCase(search,pageable);

        }else{
            users=repository.findAll(pageable);
        }
          return users.map(this::mapToResponse);
    }

    //Create User
    @Override
    public UserResponseDto createUser(UserRequestDto dto) {
        // check duplicate email
        repository.findByEmail(dto.getEmail()).ifPresent(user -> {throw new
                DuplicateResourceException("Email is already exists");});
        // encode password
         User user=  User.builder().username(dto.getUsername()).email(dto.getEmail()).
                                    password(passwordEncoder.encode(dto.getPassword())).role(Role.OFFICER).build()
                    ;
         User savedUser= repository.save(user);
        return mapToResponse(savedUser);
    }

    private UserResponseDto mapToResponse(User user) {
        return UserResponseDto.builder().email(user.getEmail()).id(user.getId())
                .role(user.getRole().toString()).username(user.getUsername()).build();
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = repository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("User not found with id: "+id));
            return mapToResponse(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        return repository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User existingUser= repository.findById(id).
                orElseThrow(()->new  ResourceNotFoundException("User not found with id: "+id));

        if(!existingUser.getEmail().equals(dto.getEmail())){
            repository.findByEmail(dto.getEmail()).ifPresent(user -> {throw new
                    DuplicateResourceException("Email is already exists");});
        }
        existingUser.setUsername(dto.getUsername());
        existingUser.setEmail(dto.getEmail());

        if(dto.getPassword() !=null && !dto.getPassword().isBlank()){
            existingUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        User updated =repository.save(existingUser);
        return mapToResponse(updated);
    }

    @Override
    public void deleteUser(Long id) {
        User user = repository.findById(id).
                orElseThrow(()->new  ResourceNotFoundException("User not found with id: "+id));

        repository.delete(user);
    }
}
