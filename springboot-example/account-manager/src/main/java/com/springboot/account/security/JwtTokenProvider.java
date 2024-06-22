package com.springboot.account.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {
    //@Value("${springboot.example.app.secret}")
    private final static String APP_SECRET="2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D";

    //@Value("${springboot.example.expiretime}")
    private final static long EXPIRES_IN=604800;

    public String generateJwtToken(Authentication auth) {
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();

        Date expiresDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder()
                .subject(userDetails.getId().toString())
                .issuedAt(new Date())
                .expiration(expiresDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET)
                .compact();
    }

    UUID getUserIdFromJWt(String token) {
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).build().parseClaimsJws(token).getPayload();
        return UUID.fromString(claims.getSubject());

    }

    boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(APP_SECRET).build().parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).build().parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

}
