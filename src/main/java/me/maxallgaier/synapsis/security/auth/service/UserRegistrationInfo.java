package me.maxallgaier.synapsis.security.auth.service;

public record UserRegistrationInfo(
    String email,
    String username,
    String password,
    String firstName,
    String lastName
) {}
