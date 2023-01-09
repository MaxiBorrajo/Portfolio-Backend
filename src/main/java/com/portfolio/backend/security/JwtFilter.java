/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author Maximiliano Borrajo
 */
@Component//Indica que es un componente de spring
public class JwtFilter extends OncePerRequestFilter {

    @Autowired //Annotation que se encarga de inicializar lo de abajo
    private JwtUtilService jwtUtilService; //servicio en relacion a jwt
    
    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl; //servicio customizado de userdetailsservice
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*Este metodo es el que se va encargar de realizar la autorization de las request,
        primero toma la seccion authorization del header, del  mismo, si lo tiene
        extrae el token de autorizacion, y obtiene el nombre de usuario, luego,
        si consigue eso, y el contexto de seguridad no esta autenticado, crea una nueva
        autenticacion, con la info del usuario usando userdetails y lo pone si credenciales,
        autenticacion usa el password como credencial pero aca no se usa, y le da pone sus autoridades
        , luego setea los detalles de  la request en autentication y setea dicha auth en
        el contexto de seguridad, luego realiza el filtro, si algo de eso no se puede, tira
        una excepcion*/
        
        String authorizationHeader  = request.getHeader("Authorization");
        String token = null;
        String username = null;
        
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            token = authorizationHeader.substring(7);
            username = jwtUtilService.getUsernameFromToken(token);
        }
        
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
           UserDetails userDetails = customUserDetailsServiceImpl.loadUserByUsername(username);
           if(jwtUtilService.validateToken(token, userDetails)){
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
                       new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
        }
        response.setHeader("Access-Control-Allow-Origin", "my-portfolio-6385d.firebaseapp.com");
        filterChain.doFilter(request, response);
    }
    
}
