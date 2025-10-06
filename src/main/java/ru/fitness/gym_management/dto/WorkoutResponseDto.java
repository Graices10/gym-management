package ru.fitness.gym_management.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkoutResponseDto {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer maxParticipants;

    private TrainerInfoDto trainer;

    @Data
    public static class TrainerInfoDto {
        private Long id;
        private String firstName;
        private String lastName;
        private String specialization;
    }
}
