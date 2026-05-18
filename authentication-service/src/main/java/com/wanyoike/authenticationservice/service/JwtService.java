package com.wanyoike.authenticationservice.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    @Value("${jjwt.secret.key}")
    private String secretKey;

    public SecretKey signingKey() {
        if (secretKey != null) {
            byte[] keyBytes = Decoders.BASE64
                    .decode(secretKey);
            return Keys.hmacShaKeyFor(keyBytes);
        } else {
            throw new IllegalStateException("SecretKey is null");
        }
    }

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return
                Jwts.builder()
                        .claims()
                        .add(claims)
                        .subject(email)
                        .issuer(email)
                        .issuedAt(new Date(System.currentTimeMillis()))
                        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) //expires after 30mins
                        .and()
                        .signWith(signingKey())
                        .compact();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(signingKey())
                    .build()
                    .parseSignedClaims(token);
        } catch (SignatureException e) {
            throw new JwtException("Invalid JWT Signature");

        } catch (JwtException e) {
            throw new JwtException("Invalid JWT");
        }
    }
}

