package com.wanyoike.apigateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretKey;

    public SecretKey signingKey() {
        if (secretKey != null) {
            byte[] keyBytes = Decoders.BASE64
                    .decode(secretKey);
            return Keys.hmacShaKeyFor(keyBytes);
        } else {
            throw new IllegalStateException("SecretKey is invalid");
        }
    }

    public Claims extractClaimsJWT(String token) {
       return  Jwts.parser()
                .verifyWith(signingKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token) {
        try {
            extractClaimsJWT(token);
            return true;
        } catch (SignatureException e) {
            throw new JwtException("Invalid JWT Signature");

        } catch (JwtException e) {
            throw new JwtException("Invalid JWT");
        }
    }
}
