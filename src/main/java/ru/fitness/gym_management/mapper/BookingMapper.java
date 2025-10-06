package ru.fitness.gym_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fitness.gym_management.dto.BookingRequestDto;
import ru.fitness.gym_management.dto.BookingResponseDto;
import ru.fitness.gym_management.entity.Booking;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking toEntity(BookingRequestDto dto);

    @Mapping(source = "client.id", target = "client.id")
    @Mapping(source = "client.firstName", target = "client.firstName")
    @Mapping(source = "client.lastName", target = "client.lastName")
    @Mapping(source = "workout.id", target = "workout.id")
    @Mapping(source = "workout.name", target = "workout.name")
    @Mapping(source = "workout.startTime", target = "workout.startTime")
    BookingResponseDto toDto(Booking booking);
}