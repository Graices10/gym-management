// ru.fitness.gym_management.service.RefreshTokenService
package ru.fitness.gym_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fitness.gym_management.entity.RefreshToken;
import ru.fitness.gym_management.entity.User;
import ru.fitness.gym_management.repository.RefreshTokenRepository;
import ru.fitness.gym_management.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    /**
     * Создаёт новый refresh token и сохраняет его в БД.
     * Удаляет старые токены пользователя для безопасности.
     */
    public RefreshToken createRefreshToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Удаляем старые refresh-токены пользователя
        refreshTokenRepository.deleteByUser(user);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setRevoked(false);

        return refreshTokenRepository.save(refreshToken);
    }

    /**
     * Проверяет, валиден ли refresh token:
     * - существует в БД
     * - не отозван
     * - JWT-токен не просрочен
     *
     * @throws RuntimeException если токен недействителен
     */
    public RefreshToken verifyExpiration(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refreshToken.isRevoked()) {
            throw new RuntimeException("Refresh token was revoked");
        }

        if (!jwtService.validateRefreshToken(token)) {
            refreshToken.setRevoked(true);
            refreshTokenRepository.save(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }

        return refreshToken;
    }

    /**
     * Отзывает все refresh-токены пользователя (например, при logout)
     */
    public void deleteByUserId(Long userId) {
        userRepository.findById(userId)
                .ifPresent(refreshTokenRepository::deleteByUser);
    }

    public void revokeRefreshToken(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        token.setRevoked(true);
        refreshTokenRepository.save(token);
    }
}
