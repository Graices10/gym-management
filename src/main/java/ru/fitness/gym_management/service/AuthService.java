package ru.fitness.gym_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.fitness.gym_management.dto.auth.AuthResponseDto;
import ru.fitness.gym_management.dto.auth.LoginRequestDto;
import ru.fitness.gym_management.dto.auth.RefreshTokenRequestDto;
import ru.fitness.gym_management.entity.RefreshToken;
import ru.fitness.gym_management.entity.User;
import ru.fitness.gym_management.repository.RefreshTokenRepository;
import ru.fitness.gym_management.security.UserDetailsImpl;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * Аутентификация пользователя и выдача access + refresh токенов
     */
    public AuthResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String accessToken = jwtService.generateToken(userDetails);

        RefreshToken refreshTokenEntity = refreshTokenService.createRefreshToken(request.getEmail());
        String refreshToken = refreshTokenEntity.getToken();

        User user = ((UserDetailsImpl) userDetails).getUser();

        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    /**
     * Обновление access token с помощью refresh token
     */
    public AuthResponseDto refreshToken(RefreshTokenRequestDto request) {
        String requestRefreshToken = request.getRefreshToken();

        RefreshToken refreshToken = refreshTokenService.verifyExpiration(requestRefreshToken);

        User user = refreshToken.getUser();
        UserDetails userDetails = new UserDetailsImpl(user);
        String newAccessToken = jwtService.generateToken(userDetails);

        return AuthResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(requestRefreshToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public String logout(String refreshToken) {
        refreshTokenService.revokeRefreshToken(refreshToken);
        return "Logged out successfully";
    }
}
