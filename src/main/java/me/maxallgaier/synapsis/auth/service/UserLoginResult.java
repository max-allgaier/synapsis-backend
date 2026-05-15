package me.maxallgaier.synapsis.auth.service;

public record UserLoginResult(String refreshToken, String accessToken) {}
