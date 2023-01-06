/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.security;

import com.portfolio.backend.model.Role;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.repository.UserRepository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maximiliano Borrajo
 */
@Service// Indica que es un servicio 
public class CustomUserDetailsServiceImpl implements UserDetailsService {
/*Esta clase implementa la interfaz userdetailsservice que tiene como metodo loaduserbyusername
    lo que hago es sobreescribir el mismo para busque en mi base datos y lo transforme la 
    clase user que es propia del paquete de userdetails de spring security*/
    
    @Autowired//Annotation que se encarga de inicializar lo de abajo
    UserRepository userRepo;//repositorio de usuario
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*Carga la usuario dado desde la bd y lo devuelve como un user de userdetails*/
        UserEntity user = userRepo.findByUserName(username).orElse(null);
        return new User(user.getUserName(), user.getPassword(), mapRolesAuthoritys(user.getRoles()));
    }
    
    private Collection<GrantedAuthority> mapRolesAuthoritys(List<Role> roles){
        /*Transforma los roles en autoridades*/
      return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
