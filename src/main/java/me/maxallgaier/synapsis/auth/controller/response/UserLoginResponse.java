package me.maxallgaier.synapsis.auth.controller.response;

public record UserLoginResponse(
    String refreshToken,
    String type,
    int expiresIn
) {}
