package me.maxallgaier.synapsis.auth.jwt.access;

import lombok.RequiredArgsConstructor;
import me.maxallgaier.synapsis.auth.jwt.JwtClaims;
import me.maxallgaier.synapsis.auth.jwt.JwtService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccessTokenService {
    private final JwtService jwtService;

    public JwtClaims validateAndParseClaims(String jwt) {
        return this.jwtService.validateAndParseClaims(jwt);
    }
}
