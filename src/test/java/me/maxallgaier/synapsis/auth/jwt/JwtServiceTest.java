package me.maxallgaier.synapsis.auth.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {
    @Test
    void generateJwt_validateAndParseClaims__CreatesAndVerifiesPayloadCorrectly() {
        var jwtProperties = new JwtProperties("test-secret-key-that-is-long-enough-for-hs256-algorithm");
        var jwtService = new JwtService(jwtProperties);

        var testId = UUID.randomUUID();
        var testExpiration = Instant.now().plus(1, ChronoUnit.HOURS);
        JwtClaims claims = new JwtClaims.Builder()
            .id(testId)
            .subject("testuser@example.com")
            .expiration(testExpiration)
            .role("ADMIN")
            .build();
        String jwt = jwtService.generateJwt(claims);

        assertNotNull(jwt);
        assertFalse(jwt.isEmpty());
        assertTrue(jwt.contains("."), "JWT should contain dots separating header, payload, and signature");

        JwtClaims parsedClaims = jwtService.validateAndParseClaims(jwt);
        assertEquals(testId, parsedClaims.id());
        assertEquals("testuser@example.com", parsedClaims.subject());
        assertEquals("ADMIN", parsedClaims.role());
        assertEquals(testExpiration.getEpochSecond(), parsedClaims.expiration().getEpochSecond());
    }
}
