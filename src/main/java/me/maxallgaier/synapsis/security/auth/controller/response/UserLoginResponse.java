package me.maxallgaier.synapsis.security.auth.controller.response;

public record UserLoginResponse(
    String refreshToken,
    String type,
    int expiresIn
) {}
