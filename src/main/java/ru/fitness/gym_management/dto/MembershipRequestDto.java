package ru.fitness.gym_management.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MembershipRequestDto {

    @NotBlank(message = "Тип абонемента обязателен")
    private String type;

    @NotNull(message = "Дата начала обязательна")
    private LocalDate startDate;

    @NotNull(message = "Дата окончания обязательна")
    @FutureOrPresent(message = "Дата окончания не может быть в прошлом")
    private LocalDate endDate;

    @NotNull(message = "Цена обязательна")
    @DecimalMin(value = "0.01", message = "Цена должна быть положительной")
    private BigDecimal price;

    @NotNull(message = "ID клиента обязателен")
    private Long clientId;
}
