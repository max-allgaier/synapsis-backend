package me.maxallgaier.synapsis.auth.service;

public record UserRegistrationInfo(
    String email,
    String username,
    String password,
    String firstName,
    String lastName
) {}
