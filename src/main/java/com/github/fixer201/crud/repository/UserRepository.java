package com.github.fixer201.crud.repository;

import com.github.fixer201.crud.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
     Optional<UserModel> findByUsername(String username);
     Optional<UserModel> findByEmail(String email);
     boolean existsByEmail(String email);
     boolean existsByUsername(String username);
}
