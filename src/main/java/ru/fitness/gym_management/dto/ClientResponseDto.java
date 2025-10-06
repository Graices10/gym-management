package ru.fitness.gym_management.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ClientResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private LocalDateTime createdAt;
}
