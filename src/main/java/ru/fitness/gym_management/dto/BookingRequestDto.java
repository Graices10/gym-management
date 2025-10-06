package ru.fitness.gym_management.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingRequestDto {

    @NotNull(message = "ID клиента обязателен")
    private Long clientId;

    @NotNull(message = "ID тренировки обязателен")
    private Long workoutId;
}
