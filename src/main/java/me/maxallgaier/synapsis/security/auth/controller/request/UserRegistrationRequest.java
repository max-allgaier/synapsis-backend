package me.maxallgaier.synapsis.security.auth.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequest(
    @NotBlank
    @Email
    String email,

    @NotBlank
    @Size(min = 3, max = 16)
    String username,

    @NotBlank
    @Size(min = 8, max = 30)
    String password,

    @NotBlank
    @Size(max = 50)
    String firstName,

    @NotBlank
    @Size(max = 50)
    String lastName
) {}
