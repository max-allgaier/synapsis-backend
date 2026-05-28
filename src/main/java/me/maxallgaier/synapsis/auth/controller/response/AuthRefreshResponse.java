package me.maxallgaier.synapsis.auth.controller.response;

public record AuthRefreshResponse(
    String accessToken,
    String tokenType,
    int expiresIn
) {}
