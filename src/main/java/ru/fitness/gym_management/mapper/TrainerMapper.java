package ru.fitness.gym_management.mapper;

import org.mapstruct.Mapper;
import ru.fitness.gym_management.dto.TrainerRequestDto;
import ru.fitness.gym_management.dto.TrainerResponceDto;
import ru.fitness.gym_management.entity.Trainer;

@Mapper(componentModel = "spring")
public interface TrainerMapper {

    Trainer toEntity(TrainerRequestDto dto);

    TrainerResponceDto toDto(Trainer trainer);
}
