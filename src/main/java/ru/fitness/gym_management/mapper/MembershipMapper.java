package ru.fitness.gym_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fitness.gym_management.dto.MembershipRequestDto;
import ru.fitness.gym_management.dto.MembershipResponseDto;
import ru.fitness.gym_management.entity.Membership;

@Mapper(componentModel = "spring")
public interface MembershipMapper {

    Membership toEntity(MembershipRequestDto dto);

    @Mapping(source = "client.id", target = "client.id")
    @Mapping(source = "client.firstName", target = "client.firstName")
    @Mapping(source = "client.lastName", target = "client.lastName")
    MembershipResponseDto toDto(Membership membership);
}
