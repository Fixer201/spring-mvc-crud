package com.github.fixer201.crud.controller;

import com.github.fixer201.crud.dto.UserLoginRequest;
import com.github.fixer201.crud.dto.UserRegisterRequest;
import com.github.fixer201.crud.dto.UserResponse;
import com.github.fixer201.crud.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // Разрешаем запросы от React
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody UserRegisterRequest request) {
        return userService.registerNewUser(request);
    }

    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/me")
    public UserResponse me() {
        return userService.getCurrentUser();
    }

}
