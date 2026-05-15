package me.maxallgaier.synapsis.auth.jwt;

import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@Builder
public record JwtClaims(UUID id, String subject, OffsetDateTime expiration, String role) {
    public Date expirationAsDate() {
        return Date.from(this.expiration.toInstant());
    }
}
