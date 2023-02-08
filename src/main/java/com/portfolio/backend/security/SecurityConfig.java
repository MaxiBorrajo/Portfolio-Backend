/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Maximiliano Borrajo
 */
@Configuration//Indica que es un config de spring en el que se van a usar bean methods
@EnableWebSecurity/*The @EnableWebSecurity is a marker annotation. 
It allows Spring to find (it's a @Configuration and, therefore, @Component) 
and automatically apply the class to the global WebSecurity.*/
@EnableMethodSecurity
public class SecurityConfig{
    /*Security config es principal encargo de filtrar quienes pueden
    acceder a la api o no*/
    
    @Autowired //Annotation que se encarga de inicializar lo de abajo
    private JwtFilter jwtFilter;//filtro de autorizacion
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{
        /*metodo principal, puesto como bean para spring lo tome como un componente suyo, y lo use.
        Se encarga del filtrado de aquello que llega a la api, desabilita dos cositas,
        luego dice que aquellos pedidos con /auth/* no tienen que pedir authorizacion pero
        los demas si, luego un par de cositas mas y por ultimo establecemos el filtrado principal
        que es el jwtfilter que depende del token*/
         return http
            .csrf().disable()
            .cors().and()
            .authorizeHttpRequests()
            .antMatchers("/auth/*").permitAll()
            .antMatchers("/contacto/*").permitAll()
            .antMatchers("/role/*").permitAll()
            .antMatchers(HttpMethod.GET).permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(
            /*Retorna un autenticacion manager, puesto como componente de spring*/
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    PasswordEncoder passwordEncoder(){
        /*Retorna un encriptador de contraseñas, puesto como componente de spring*/
        return new BCryptPasswordEncoder();
    }
    
    
}
