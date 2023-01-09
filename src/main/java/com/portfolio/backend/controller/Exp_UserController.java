/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.controller;

import com.portfolio.backend.model.Exp_User;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.service.Exp_UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maximiliano Borrajo
 */
@RestController//annotation que indica que esto es una api rest
@RequestMapping("exp_user")//es como un prefijo de la url para esta, /user/* es algo de esta api
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "true",
exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"} )
public class Exp_UserController {
    @Autowired//Annotation que se encarga de inicializar lo de abajo
    private Exp_UserServiceImpl expService;//servicio que maneja la bd de usuarios
    
    @Autowired
    private UserController userController;
    
    @GetMapping("{username}")
    public List<Exp_User> getExpUserByUsername(@PathVariable(value = "username") String username){
        UserEntity user = userController.getUser(username);
        return expService.findExpUserByUser(user);
    }
    
    @GetMapping
    public Exp_User getExpUserById(@RequestParam Long id){
        return expService.findExpUserById(id);
    }
    
    @PostMapping
    public ResponseEntity<String> saveExpUser(@RequestBody Exp_User expUser, @RequestParam String username){
        UserEntity user = userController.getUser(username);
        expUser.setUserEntity(user);
        expService.saveExpUser(expUser);
        return new ResponseEntity<>("Save succesful", HttpStatus.OK);
    }
    
    
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteExpUser(@PathVariable(value = "id") Long id){
        expService.deleteExpUserById(id);
        return new ResponseEntity<>("Delete succesful", HttpStatus.OK);
    }
}
