package com.github.fixer201.crud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
public class UserRegisterRequest {


    @NotBlank(message = "Имя пользователя обязательно")
    @Size(min = 4, max = 64, message = "Имя пользователя должно быть от 4 до 64 символов")
    private String username;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный email")
    private String email;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, max = 256, message = "Пароль должен быть от 6 до 256 символов")
    @ToString.Exclude
    private String password;

}
