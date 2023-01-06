/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maximiliano Borrajo
 */
@Service// Indica que es un servicio 
public class JwtUtilService {
    
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);//clave secreta que se usa para firmar el jwt
    
    private String createToken(Map<String, Object> claims, String subject){
        //Se encarga de crear el token de autorizacion
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
    
    public String generateToken(String username){
        //Termina de crear el token digamos
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }
    
    public Boolean validateToken(String token, UserDetails userDetails){
        /* valida si el token es correcto si el username que esta ahi
        existe como tal y si no esta expirado
        */
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) &&
                !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        /*Obtiene el username del token*/
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    private boolean isTokenExpired(String token) {
        /*Indica si el token esta expirado*/
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration().before(new Date());
    }
}
