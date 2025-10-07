package ru.fitness.gym_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fitness.gym_management.dto.auth.AuthResponseDto;
import ru.fitness.gym_management.dto.auth.LoginRequestDto;
import ru.fitness.gym_management.dto.auth.RefreshTokenRequestDto;
import ru.fitness.gym_management.dto.auth.RegisterRequestDto;
import ru.fitness.gym_management.service.AuthService;
import ru.fitness.gym_management.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDto request) {
        userService.registerClient(request);
        return ResponseEntity.status(201).body("Клиент успешно зарегистрирован");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refreshToken(@RequestBody RefreshTokenRequestDto request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequestDto request) {
        authService.logout(request.getRefreshToken());
        return ResponseEntity.ok("Logged out successfully");
    }
}