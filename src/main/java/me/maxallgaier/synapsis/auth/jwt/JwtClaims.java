package me.maxallgaier.synapsis.auth.jwt;

import lombok.Builder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Builder
public record JwtClaims(
    UUID id,
    String subject,
    Instant expiration,
    String role
) {
    public JwtClaims {
        expiration = expiration.truncatedTo(ChronoUnit.SECONDS);
    }

    public Date expirationAsDate() {
        return Date.from(this.expiration);
    }
}
