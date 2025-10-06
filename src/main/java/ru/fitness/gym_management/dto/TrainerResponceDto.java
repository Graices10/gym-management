package ru.fitness.gym_management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TrainerResponceDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String specialization;

    private String phone;

    private String email;
}
