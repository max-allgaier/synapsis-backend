package me.maxallgaier.synapsis.auth.jwt.refresh;

import lombok.RequiredArgsConstructor;
import me.maxallgaier.synapsis.auth.jwt.JwtClaims;
import me.maxallgaier.synapsis.auth.jwt.JwtHelper;
import me.maxallgaier.synapsis.user.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private static final int DAYS_IN_SECONDS = 24 * 60 * 60;
    private final JwtHelper jwtHelper;

    public JwtClaims parseClaims(String refreshToken) {
        return this.jwtHelper.validateAndParseClaims(refreshToken);
    }

    public RefreshToken generate(User user) {
        int expiresInSeconds = 30 * DAYS_IN_SECONDS;
        var jwtClaims = JwtClaims.builder()
            .id(UUID.randomUUID())
            .subject(user.username())
            .expiration(Instant.now().plusSeconds(expiresInSeconds))
            .role(user.role().toString())
            .build();
        var jwt = this.jwtHelper.generateJwt(jwtClaims);

        return RefreshToken.builder()
            .token(jwt)
            .expiresIn(expiresInSeconds)
            .build();
    }
}
