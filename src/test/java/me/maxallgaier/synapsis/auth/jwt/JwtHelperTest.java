package me.maxallgaier.synapsis.auth.jwt;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtHelperTest {
    @Test
    void createsAndVerifiesPayloadCorrectly() {
        var jwtProperties = new JwtProperties("test-secret-key-that-is-long-enough-for-hs256-algorithm");
        var jwtHelper = new JwtHelper(jwtProperties);

        JwtClaims expectedJwtClaims = JwtClaims.builder()
            .id(UUID.randomUUID())
            .subject("testuser@example.com")
            .expiration(Instant.now().plus(1, ChronoUnit.HOURS))
            .role("ADMIN")
            .build();
        String jwt = jwtHelper.generateJwt(expectedJwtClaims);

        assertEquals(expectedJwtClaims, jwtHelper.validateAndParseClaims(jwt));
    }
}
