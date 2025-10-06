package ru.fitness.gym_management.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkoutRequestDto {

    @NotBlank(message = "Название тренировки обязательно")
    private String name;

    private String description;

    @NotNull(message = "Время начала обязательно")
    @Future(message = "Время начала должно быть в будущем")
    private LocalDateTime startTime;

    @NotNull(message = "Время окончания обязательно")
    private LocalDateTime endTime;

    @Positive(message = "Максимальное число участников должно быть больше 0")
    private Integer maxParticipants;

    @NotNull(message = "ID тренера обязателен")
    private Long trainerId;
}
