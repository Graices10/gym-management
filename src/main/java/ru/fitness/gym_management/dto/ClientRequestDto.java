package ru.fitness.gym_management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientRequestDto {

    @NotBlank(message = "Имя обязательно для заполнения")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна для заполнения")
    private String lastName;

    private String phone;
    private String email;
}
