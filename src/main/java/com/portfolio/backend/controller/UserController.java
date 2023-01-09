/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.controller;

import com.portfolio.backend.model.InfoUser;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.service.UserEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maximiliano Borrajo
 */
@RestController//annotation que indica que esto es una api rest
@RequestMapping("user")//es como un prefijo de la url para esta, /user/* es algo de esta api
@CrossOrigin(origins="*")//indica que url pueden acceder a esta api
public class UserController {
    @Autowired//Annotation que se encarga de inicializar lo de abajo
    private UserEntityServiceImpl userService;//servicio que maneja la bd de usuarios

    
    @GetMapping("{username}")//metodo get y url
    public UserEntity getUser(@PathVariable(value = "username") String username){
        /*metodo que describe la info del usuario dado en la url*/
        return userService.getUserByUsername(username);
    }
    
    @GetMapping("info/{username}")
    public InfoUser getInfoUserByUsername(@PathVariable(value = "username") String username){
        InfoUser info = userService.getUserByUsername(username).getInfoUser();
        return info;
    }
    
    @DeleteMapping("{username}")//metodo delete y url
    public void deleteUser(@PathVariable(value = "username") String username){
        /*metodo que elimina al usuario dado en la url de la bd*/
        userService.deleteUserByUsername(username);
    }
}
