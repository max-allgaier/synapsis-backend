package me.maxallgaier.synapsis.auth.jwt;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtServiceTest {
    @Test
    void createsAndVerifiesPayloadCorrectly() {
        var jwtProperties = new JwtProperties("test-secret-key-that-is-long-enough-for-hs256-algorithm");
        var jwtService = new JwtService(jwtProperties);

        JwtClaims expectedJwtClaims = JwtClaims.builder()
            .id(UUID.randomUUID())
            .subject("testuser@example.com")
            .expiration(Instant.now().plus(1, ChronoUnit.HOURS))
            .role("ADMIN")
            .build();
        String jwt = jwtService.generateJwt(expectedJwtClaims);

        assertEquals(expectedJwtClaims, jwtService.validateAndParseClaims(jwt));
    }
}
