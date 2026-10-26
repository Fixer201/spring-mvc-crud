package com.github.fixer201.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Map;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register", "/api/auth/login", "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // Хеши сохраняются с префиксом {argon2}. Если позже сменить алгоритм,
    // старые пароли продолжат проверяться по этому префиксу.
    @Bean
    public PasswordEncoder passwordEncoder() {
        String encoderId = "argon2";
        Map<String, PasswordEncoder> encoders = Map.of(
                encoderId,  new Argon2PasswordEncoder(16, 32, 1, 23552, 3)
        );
        return new DelegatingPasswordEncoder(encoderId, encoders);
    }
}
