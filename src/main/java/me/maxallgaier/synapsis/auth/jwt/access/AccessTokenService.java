package me.maxallgaier.synapsis.auth.jwt.access;

import lombok.RequiredArgsConstructor;
import me.maxallgaier.synapsis.auth.jwt.JwtClaims;
import me.maxallgaier.synapsis.auth.jwt.JwtService;
import me.maxallgaier.synapsis.auth.jwt.refresh.RefreshTokenService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccessTokenService {
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    public JwtClaims parseClaims(String jwt) {
        return this.jwtService.validateAndParseClaims(jwt);
    }

    public String generate(String refreshTokenJwt) {
        var refreshTokenClaims = this.refreshTokenService.parseClaims(refreshTokenJwt);
        var accessTokenClaims = JwtClaims.builder()
            .id(UUID.randomUUID())
            .subject(refreshTokenClaims.subject())
            .expiration(Instant.now().plus(60, ChronoUnit.MINUTES))
            .role(refreshTokenClaims.role())
            .build();
        return this.jwtService.generateJwt(accessTokenClaims);
    }
}
