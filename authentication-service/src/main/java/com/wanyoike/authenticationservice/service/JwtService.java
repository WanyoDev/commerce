package com.wanyoike.authenticationservice.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    //secret key in string format
    @Value("${jjwt.secret.key}")
    public static String generatedSK;
    
    public SecretKey signingKey() {
        byte[] keyBytes = Decoders.BASE64
                .decode(generatedSK);
        return Keys.hmacShaKeyFor(keyBytes);
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
                    .verifyWith(signingKey()) //this is the generated secret key
                    .build()
                    .parseSignedClaims(token);
        } catch (SignatureException e) {
            throw new JwtException("Invalid JWT Signature");

        } catch (JwtException e) {
            throw new JwtException("Invalid JWT");
        }
    }
}

