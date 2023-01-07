/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.controller;

import com.portfolio.backend.dto.UserDto;
import com.portfolio.backend.model.Role;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.security.CustomUserDetailsServiceImpl;
import com.portfolio.backend.security.JwtUtilService;
import com.portfolio.backend.service.RoleServiceImpl;
import com.portfolio.backend.service.UserEntityServiceImpl;
import java.security.Principal;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maximiliano Borrajo
 */
@RestController //annotation que indica que esto es una api rest
@RequestMapping("auth") //es como un prefijo de la url para esta, /auth/* es algo de esta api
@CrossOrigin(origins = "https://my-portfolio-6385d.web.app")//indica que url pueden acceder a esta api
public class AuthController {
    
    @Autowired //Annotation que se encarga de inicializar lo de abajo
    private JwtUtilService jwtUtilService;//servicio en relacion a jwt
    
    @Autowired
    private UserEntityServiceImpl userService;//servicio que maneja la bd de usuarios
    
    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;
    
    @Autowired
    private RoleServiceImpl roleService;//servicio que maneja la bd de roles
    
    @Autowired
    private PasswordEncoder passwordEncoder;//interfaz que se encarga de encriptar contraseñas
    
    @Autowired
    private AuthenticationManager authManager;//interfaz que se encarga de autenticar segun la autenticacion que le pases
    
    @PostMapping("login")//metodo post y su url
    public String login(@RequestBody UserDto user) throws Exception{
        /*metodo que se encarga de realizar el login y autenticar al usuario,
        si lo logra, regresa un token de autorization, sino envia una exception*/
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(),
                            user.getPassword(), Collections.emptyList())
            );
        } catch (Exception e) {
            throw new Exception("Invalid username/password");
        }
        String token = jwtUtilService.generateToken(user.getUserName());
        return token;
    }
    
    @PostMapping("register")//metodo post y su url
    public ResponseEntity<String> register(@RequestBody UserEntity user){
        /*metodo que se encarga de registrar usuarios, si el usuario existe
        te dice que ya está y te tira un bad request, luego si el usuario no existe
        te lo crea, primera encripta tu password, luego te asigna el rol de usuario
        de forma predeterminada y luego te guarda en la bd*/
        if(userService.existsByUsername(user.getUserName())){
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Role roles = roleService.getRoleByName("ADMIN");
            user.setRoles(Collections.singletonList(roles));
            userService.saveUser(user);
            return new ResponseEntity<>("Register succesful", HttpStatus.OK);
        }
    }
    
    @GetMapping("active-user")
    public User getActiveUser(Principal principal){
        return (User) customUserDetailsService.loadUserByUsername(principal.getName());
    }
}
