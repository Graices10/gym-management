package ru.fitness.gym_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fitness.gym_management.dto.MembershipRequestDto;
import ru.fitness.gym_management.dto.MembershipResponseDto;
import ru.fitness.gym_management.entity.Client;
import ru.fitness.gym_management.entity.Membership;
import ru.fitness.gym_management.mapper.MembershipMapper;
import ru.fitness.gym_management.repository.ClientRepository;
import ru.fitness.gym_management.repository.MembershipRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final ClientRepository clientRepository;
    private final MembershipMapper membershipMapper;

    public List<MembershipResponseDto> findAll() {
        return membershipRepository.findAll().stream()
                .map(membershipMapper::toDto)
                .collect(Collectors.toList());
    }

    public MembershipResponseDto findById(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Абонемент с id " + id + " не найден"));
        return membershipMapper.toDto(membership);
    }

    public MembershipResponseDto create(MembershipRequestDto requestDto) {
        Client client = clientRepository.findById(requestDto.getClientId())
                .orElseThrow(() -> new RuntimeException("Клиент с id " + requestDto.getClientId() + " не найден"));

        Membership membership = membershipMapper.toEntity(requestDto);
        membership.setClient(client);


        Membership saved = membershipRepository.save(membership);
        return membershipMapper.toDto(saved);
    }

    public MembershipResponseDto update(Long id, MembershipRequestDto requestDto) {
        Membership existing = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Абонемент с id " + id + " не найден"));

        Client client = clientRepository.findById(requestDto.getClientId())
                .orElseThrow(() -> new RuntimeException("Клиент с id " + requestDto.getClientId() + " не найден"));

        existing.setType(requestDto.getType());
        existing.setStartDate(requestDto.getStartDate());
        existing.setEndDate(requestDto.getEndDate());
        existing.setPrice(requestDto.getPrice());
        existing.setClient(client);

        Membership updated = membershipRepository.save(existing);
        return membershipMapper.toDto(updated);
    }

    public void deleteById(Long id) {
        if (!membershipRepository.existsById(id)) {
            throw new RuntimeException("Абонемент с id " + id + " не найден");
        }
        membershipRepository.deleteById(id);
    }
}
