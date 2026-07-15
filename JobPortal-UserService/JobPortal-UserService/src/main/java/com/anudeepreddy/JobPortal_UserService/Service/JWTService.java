package com.anudeepreddy.JobPortal_UserService.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;


    public String generateToken(String userMail) {
        Map<String , Object> claims=new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(userMail)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public Claims extractAllDetail(String token){
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }

    public String extractUserMail(String token)
    {
        return extractAllDetail(token).getSubject();
    }

    public Date extractExpiration(String token){
        return extractAllDetail(token).getExpiration();
    }

    public boolean isExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean isValid(String token, UserDetails userDetails){
        final String userMail=extractUserMail(token);
        return (userMail.equals(userDetails.getUsername()))&&(!isExpired(token));
    }

}
