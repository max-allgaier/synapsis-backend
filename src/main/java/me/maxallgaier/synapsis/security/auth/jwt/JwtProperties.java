package me.maxallgaier.synapsis.security.auth.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(String secret) {
    public SecretKey getHmacSha() {
        return Keys.hmacShaKeyFor(this.secret().getBytes(StandardCharsets.UTF_8));
    }
}
