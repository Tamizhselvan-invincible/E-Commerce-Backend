package com.hetero.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final String secretKey;


    public  JwtService(){
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey key = keyGen.generateKey();

            secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }

    }

    public String generateToken (String username) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (7L * 24 * 60 * 60 * 1000)))// 7 days
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey () {
          return Keys.hmacShaKeyFor(secretKey.getBytes());
    }


/// Important Note: This code is compatible with jjwt (Jose4J wrapper) versions 0.12.5 and above.
/// If you are using a version of jjwt below 0.12.5, the method signatures or parameters might differ,
/// which could lead to compilation errors or unexpected behavior. In such cases, ensure that:
///
/// 1. You update your jjwt library to at least version 0.12.5.
/// 2. If updating is not possible, modify the code accordingly to match your jjwt version's API.
///
/// Always verify your jjwt dependency in your build.gradle or pom.xml file to ensure compatibility.


    public String extractUserNameFormToken (String jwtToken) {
        return extractClaims(jwtToken, Claims::getSubject);
    }


    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken (String jwtToken, UserDetails userDetails) {
         final String userName = extractUserNameFormToken(jwtToken);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired (String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration (String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}
