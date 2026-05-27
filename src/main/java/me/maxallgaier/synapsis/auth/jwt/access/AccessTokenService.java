package me.maxallgaier.synapsis.auth.jwt.access;

import lombok.RequiredArgsConstructor;
import me.maxallgaier.synapsis.auth.jwt.JwtClaims;
import me.maxallgaier.synapsis.auth.jwt.JwtHelper;
import me.maxallgaier.synapsis.auth.jwt.refresh.RefreshTokenService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccessTokenService {
    private final RefreshTokenService refreshTokenService;
    private final JwtHelper jwtHelper;

    public JwtClaims parseClaims(String accessToken) {
        return this.jwtHelper.validateAndParseClaims(accessToken);
    }

    public AccessToken generate(String refreshToken) {
        var refreshTokenClaims = this.refreshTokenService.parseClaims(refreshToken);

        int expiresInSeconds = 60 * 60;
        var accessTokenClaims = JwtClaims.builder()
            .id(UUID.randomUUID())
            .subject(refreshTokenClaims.subject())
            .expiration(Instant.now().plusSeconds(expiresInSeconds))
            .role(refreshTokenClaims.role())
            .build();
        String token = this.jwtHelper.generateJwt(accessTokenClaims);

        return AccessToken.builder()
            .token(token)
            .expiresIn(expiresInSeconds)
            .build();
    }
}
