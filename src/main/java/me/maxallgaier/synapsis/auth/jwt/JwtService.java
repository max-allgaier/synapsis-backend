package me.maxallgaier.synapsis.auth.jwt;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class JwtService {
    private final SecretKey secretKey;
    private final JwtParser parser;

    public JwtService(JwtProperties jwtProperties) {
        this.secretKey = jwtProperties.getHmacSha();
        this.parser = Jwts.parser().verifyWith(this.secretKey).build();
    }

    public String generateJwt(JwtClaims jwtClaims) {
        return Jwts.builder()
            .id(jwtClaims.id().toString())
            .subject(jwtClaims.subject())
            .expiration(jwtClaims.expirationAsDate())
            .claim("role", jwtClaims.role())
            .signWith(this.secretKey)
            .compact();
    }

    public JwtClaims validateAndParseClaims(String jwt) {
        var claims = this.parser.parseSignedClaims(jwt).getPayload();
        return new JwtClaims(
            UUID.fromString(claims.getId()),
            claims.getSubject(),
            claims.getExpiration().toInstant().atOffset(ZoneOffset.UTC),
            claims.get("role", String.class)
        );
    }
}
