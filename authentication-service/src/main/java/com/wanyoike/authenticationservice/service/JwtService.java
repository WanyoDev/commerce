package com.wanyoike.authenticationservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
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

    public static String secretKey;

    KeyGenerator keyGen;
    SecretKey sk;

    {
        try {
            keyGen = KeyGenerator.getInstance("HmacSHA256");
            sk = keyGen.generateKey();
            secretKey = Base64.getEncoder()
                    .encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public Key signingKey() {
        byte[] keyBytes = Decoders.BASE64
                .decode(secretKey);
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
                        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //expires after 10hrs
                        .and()
                        .signWith(signingKey())
                        .compact();
    }
}
