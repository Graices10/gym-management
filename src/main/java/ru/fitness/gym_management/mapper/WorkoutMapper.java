package ru.fitness.gym_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fitness.gym_management.dto.WorkoutRequestDto;
import ru.fitness.gym_management.dto.WorkoutResponseDto;
import ru.fitness.gym_management.entity.Workout;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    Workout toEntity(WorkoutRequestDto dto);

    @Mapping(source = "trainer.id", target = "trainer.id")
    @Mapping(source = "trainer.firstName", target = "trainer.firstName")
    @Mapping(source = "trainer.lastName", target = "trainer.lastName")
    @Mapping(source = "trainer.specialization", target = "trainer.specialization")
    WorkoutResponseDto toDto(Workout workout);

}
