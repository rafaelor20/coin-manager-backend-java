package com.example.coinmanager.dto;

import java.sql.Date;
import jakarta.validation.constraints.NotBlank;

public record UsersDto(
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank Date createdAt) {
}
