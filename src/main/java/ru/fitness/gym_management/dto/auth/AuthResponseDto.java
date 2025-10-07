package ru.fitness.gym_management.dto.auth;

import lombok.Builder;
import lombok.Data;
import ru.fitness.gym_management.enums.Role;

@Data
@Builder
public class AuthResponseDto {
    private String accessToken;
    private String refreshToken;
    private String email;
    private Role role;
}