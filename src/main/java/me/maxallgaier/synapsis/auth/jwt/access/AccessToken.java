package me.maxallgaier.synapsis.auth.jwt.access;

import lombok.Builder;

@Builder
public record AccessToken(
    String token,
    String type,
    int expiresIn
) {}
