package me.maxallgaier.synapsis.auth.jwt;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.UUID;

/**
 * Service responsible for any JWT related actions, including generating and validating JSON Web Tokens (JWTs).
 */
@Service
public class JwtHelper {
    private final SecretKey secretKey;
    private final JwtParser parser;

    /**
     * Initializes a JwtService with the provided JwtProperties.
     *
     * @param jwtProperties The properties used to configure the JWT service.
     */
    public JwtHelper(JwtProperties jwtProperties) {
        this.secretKey = jwtProperties.getHmacSha();
        this.parser = Jwts.parser().verifyWith(this.secretKey).build();
    }

    /**
     * Generates a JWT string based on the provided claims.
     *
     * @param jwtClaims The claims to include in the JWT
     * @return The generated JWT string
     */
    public String generateJwt(JwtClaims jwtClaims) {
        return Jwts.builder()
            .id(jwtClaims.id().toString())
            .subject(jwtClaims.subject())
            .expiration(jwtClaims.expirationAsDate())
            .claim("role", jwtClaims.role())
            .signWith(this.secretKey)
            .compact();
    }

    /**
     * Validates the provided JWT string and parses its claims. Validation includes checking
     * the signature and expiration. If the JWT is not valid, an exception is thrown.
     *
     * @param jwt The JWT string to validate and parse.
     * @return The parsed claims.
     */
    public JwtClaims validateAndParseClaims(String jwt) {
        var claims = this.parser.parseSignedClaims(jwt).getPayload();
        return JwtClaims.builder()
            .id(UUID.fromString(claims.getId()))
            .subject(claims.getSubject())
            .expiration(claims.getExpiration().toInstant())
            .role(claims.get("role", String.class))
            .build();
    }
}
