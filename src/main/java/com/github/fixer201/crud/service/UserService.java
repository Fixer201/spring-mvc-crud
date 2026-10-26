package com.github.fixer201.crud.service;

import com.github.fixer201.crud.dto.UserRegisterRequest;
import com.github.fixer201.crud.dto.UserResponse;
import com.github.fixer201.crud.model.Role;
import com.github.fixer201.crud.model.UserModel;
import com.github.fixer201.crud.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public UserResponse registerNewUser(UserRegisterRequest request) {
        UserModel user = new UserModel();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setRole(Role.USER);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        UserModel savedUser = userRepository.save(user);

        return toResponse(savedUser);
    }

    private UserResponse toResponse(UserModel user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt().toString())
                .build();
    }

    public UserResponse getCurrentUser() {
        // что-то тут надо сделать, наверное по типу сессий
        return UserResponse.builder()
                .username("fixer")
                .email("fixer@mail.ru")
                .role(Role.USER)
                .createdAt("sometime")
                .build();
    }
}
