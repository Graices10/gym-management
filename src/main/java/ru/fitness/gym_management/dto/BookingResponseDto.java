package ru.fitness.gym_management.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponseDto {

    private Long id;
    private LocalDateTime bookedAt;
    private boolean attended;

    private ClientInfoDto client;
    private WorkoutInfoDto workout;

    @Data
    public static class ClientInfoDto{
        private Long id;
        private String firstName;
        private String lastName;
    }

    @Data
    public static class WorkoutInfoDto{
        private Long id;
        private String name;
        private LocalDateTime startTime;
    }



}
