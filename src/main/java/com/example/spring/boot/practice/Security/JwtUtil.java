package com.example.spring.boot.practice.Security;


import ch.qos.logback.core.net.server.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String secretKey;

    public boolean validateToken(String token, String  username){
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
// map used to pass many information. but here we pass null. we can pass in future.
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
// map empty object passed.
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }

    public String extractUsername(String token){
       return extractClaim(token, Claims::getSubject);
//       Claims::getSubject(method reference) of ==> Claims->Claims.getSubject()
//        below code also accepted.
//        Claims claims = extractAllClaims(token);
//        return claims.getSubject();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
//      regular date comparison
    }

    public Date extractExpiration(String token){
//        return extractClaim(token, Claims.getExpiration());
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
//        Funtion -> functional interface has apply() method
//        Function<Claims, T> =? claims(paramter), T- return type(generic)
//        we pass Claims.getSubject() as a parameter. inside apply method this funcion
//        executed. claims(parameter) -> claims.getSubject() -executed
        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims); // claims argument passed. received and executed as claims.getSubject
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }


}
