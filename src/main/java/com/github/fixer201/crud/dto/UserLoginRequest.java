package com.github.fixer201.crud.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
public class UserLoginRequest {
    @NotBlank(message = "email или username обязателен")
    private String emailOrUsername;

    @NotBlank(message = "пароль обязателен")
    @ToString.Exclude
    private String password;

}
