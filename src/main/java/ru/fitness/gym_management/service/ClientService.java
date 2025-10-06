package ru.fitness.gym_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fitness.gym_management.dto.ClientRequestDto;
import ru.fitness.gym_management.dto.ClientResponseDto;
import ru.fitness.gym_management.entity.Client;
import ru.fitness.gym_management.mapper.ClientMapper;
import ru.fitness.gym_management.repository.ClientRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public List<ClientResponseDto> findAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    public ClientResponseDto findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Клиент с id " + id + " не найден"));
        return clientMapper.toDto(client);
    }

    public ClientResponseDto create(ClientRequestDto requestDto) {
        Client client = clientMapper.toEntity(requestDto);
        client.setCreatedAt(LocalDateTime.now()); // ← устанавливаем дату создания
        Client saved = clientRepository.save(client);
        return clientMapper.toDto(saved);
    }

    public ClientResponseDto update(Long id, ClientRequestDto requestDto) {
        Client existing = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Клиент с id " + id + " не найден"));

        existing.setFirstName(requestDto.getFirstName());
        existing.setLastName(requestDto.getLastName());
        existing.setPhone(requestDto.getPhone());
        existing.setEmail(requestDto.getEmail());

        Client updated = clientRepository.save(existing);
        return clientMapper.toDto(updated);
    }

    public void deleteById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Клиент с id " + id + " не найден");
        }
        clientRepository.deleteById(id);
    }
}
