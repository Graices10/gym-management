package ru.fitness.gym_management.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MembershipResponseDto {

    private Long id;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
    private boolean isActive;

    private ClientInfoDto client;

    @Data
    public static class ClientInfoDto {
        private Long id;
        private String firstName;
        private String lastName;
    }
}
