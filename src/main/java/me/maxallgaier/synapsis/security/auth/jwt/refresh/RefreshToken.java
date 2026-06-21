package me.maxallgaier.synapsis.security.auth.jwt.refresh;

import lombok.Builder;

@Builder
public record RefreshToken(
    String token,
    String type,
    int expiresIn
) {}
