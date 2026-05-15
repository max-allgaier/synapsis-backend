package me.maxallgaier.synapsis.auth.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record AuthRefreshRequest(
    @NotEmpty
    String refreshToken
) {}
