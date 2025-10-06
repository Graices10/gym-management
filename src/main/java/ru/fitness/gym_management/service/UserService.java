package ru.fitness.gym_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.fitness.gym_management.dto.auth.RegisterRequestDto;
import ru.fitness.gym_management.entity.Client;
import ru.fitness.gym_management.enums.Role;
import ru.fitness.gym_management.entity.User;
import ru.fitness.gym_management.repository.ClientRepository;
import ru.fitness.gym_management.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerClient(RegisterRequestDto request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        Client client = new Client();
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setPhone(request.getPhone());
        client.setEmail(request.getEmail());
        Client savedClient = clientRepository.save(client);

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CLIENT);
        user.setClient(savedClient);

        userRepository.save(user);
    }
}
