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

    public JwtClaims parseClaims(String refreshTokenJwt) {
        return this.jwtService.validateAndParseClaims(refreshTokenJwt);
    }

    public String generate(User user) {
        var jwtClaims = JwtClaims.builder()
            .id(UUID.randomUUID())
            .subject(user.username())
            .expiration(OffsetDateTime.now().plusDays(30))
            .role(user.role().toString())
            .build();
        return this.jwtService.generateJwt(jwtClaims);
    }
}
