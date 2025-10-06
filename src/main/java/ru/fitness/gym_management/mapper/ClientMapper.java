package ru.fitness.gym_management.mapper;

import org.mapstruct.Mapper;
import ru.fitness.gym_management.dto.ClientRequestDto;
import ru.fitness.gym_management.dto.ClientResponseDto;
import ru.fitness.gym_management.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientRequestDto dto);
    ClientResponseDto toDto(Client client);
}
