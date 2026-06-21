package me.maxallgaier.synapsis.security.auth.controller.response;

public record AuthRefreshResponse(
    String accessToken,
    String tokenType,
    int expiresIn
) {}
