package me.maxallgaier.synapsis.user;

public record UserCreateInfo(
    String email,
    String username,
    String passwordHash,
    String firstName,
    String lastName
) {}
