package me.maxallgaier.synapsis.security.auth.controller.request;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
    @NotBlank
    String email,

    @NotBlank
    String password
) {}
