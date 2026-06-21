package me.maxallgaier.synapsis.security.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.maxallgaier.synapsis.security.auth.controller.request.AuthRefreshRequest;
import me.maxallgaier.synapsis.security.auth.controller.request.UserLoginRequest;
import me.maxallgaier.synapsis.security.auth.controller.request.UserRegistrationRequest;
import me.maxallgaier.synapsis.security.auth.controller.response.AuthRefreshResponse;
import me.maxallgaier.synapsis.security.auth.controller.response.UserLoginResponse;
import me.maxallgaier.synapsis.security.auth.service.AuthService;
import me.maxallgaier.synapsis.security.auth.service.UserRegistrationInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
        var refreshToken = this.authService.login(request.email(), request.password());
        var userLoginResponse = new UserLoginResponse(
            refreshToken.token(),
            refreshToken.type(),
            refreshToken.expiresIn()
        );
        return ResponseEntity.status(HttpStatus.OK).body(userLoginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRegistrationRequest request) {
        var userRegistrationInfo = new UserRegistrationInfo(
            request.email(),
            request.username(),
            request.password(),
            request.firstName(),
            request.lastName()
        );
        this.authService.register(userRegistrationInfo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthRefreshResponse> refresh(@Valid @RequestBody AuthRefreshRequest request) {
        var accessToken = this.authService.refresh(request.refreshToken());
        var authRefreshResponse = new AuthRefreshResponse(
            accessToken.token(),
            accessToken.type(),
            accessToken.expiresIn()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(authRefreshResponse);
    }
}
