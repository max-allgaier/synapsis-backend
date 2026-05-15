package me.maxallgaier.synapsis.auth.service;

import lombok.RequiredArgsConstructor;
import me.maxallgaier.synapsis.auth.jwt.access.AccessTokenService;
import me.maxallgaier.synapsis.auth.jwt.refresh.RefreshTokenService;
import me.maxallgaier.synapsis.user.User;
import me.maxallgaier.synapsis.user.UserCreateInfo;
import me.maxallgaier.synapsis.user.UserService;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@NullMarked
@Service
public class AuthService {
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final AccessTokenService accessTokenService;
    private final PasswordEncoder passwordEncoder;

    public User register(UserRegistrationInfo info) {
        var hashedPassword = this.passwordEncoder.encode(info.password());
        if (hashedPassword == null) {
            throw new RuntimeException("null password hash");
        }

        var userCreateInfo = new UserCreateInfo(
            info.email(), info.username(), hashedPassword,
            info.firstName(), info.lastName()
        );
        return this.userService.create(userCreateInfo);
    }

    public UserLoginResult login(String email, String password) {
        var user = this.userService.findByEmail(email)
            .orElseThrow(InvalidCredentialsException::new);

        var correctLoginPassword = this.passwordEncoder.matches(password, user.passwordHash());
        if (!correctLoginPassword) {
            throw new InvalidCredentialsException();
        }

        var refreshToken = this.refreshTokenService.generate(user);
        var accessToken = this.accessTokenService.validateAndGenerate(refreshToken);

        return new UserLoginResult(refreshToken, accessToken);
    }

    public String refresh(String refreshToken) {
        return this.accessTokenService.validateAndGenerate(refreshToken);
    }
}
