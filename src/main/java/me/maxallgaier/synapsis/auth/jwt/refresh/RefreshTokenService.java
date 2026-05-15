package me.maxallgaier.synapsis.auth.jwt.refresh;

import lombok.RequiredArgsConstructor;
import me.maxallgaier.synapsis.auth.jwt.JwtClaims;
import me.maxallgaier.synapsis.auth.jwt.JwtService;
import me.maxallgaier.synapsis.user.User;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final JwtService jwtService;

    public String generate(User user) {
        var jwtClaims = new JwtClaims(
            UUID.randomUUID(),
            user.username(),
            OffsetDateTime.now().plusDays(30),
            user.role().toString()
        );
        return this.jwtService.generateJwt(jwtClaims);
    }

    public String validateAndGenerateAccessToken(String refreshTokenJwt) {
        var refreshTokenClaims = this.jwtService.validateAndParseClaims(refreshTokenJwt);
        var accessTokenClaims = JwtClaims.builder()
            .id(UUID.randomUUID())
            .subject(refreshTokenClaims.subject())
            .expiration(OffsetDateTime.now().plusDays(30))
            .role(refreshTokenClaims.role())
            .build();
        return this.jwtService.generateJwt(accessTokenClaims);
    }
}
