package me.maxallgaier.synapsis.security.auth.jwt.access;

import lombok.Builder;

@Builder
public record AccessToken(
    String token,
    String type,
    int expiresIn
) {}
