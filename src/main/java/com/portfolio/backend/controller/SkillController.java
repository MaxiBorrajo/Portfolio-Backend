/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.controller;

import com.portfolio.backend.model.Skill;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.service.SkillServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author usuario
 */
@RestController//annotation que indica que esto es una api rest
@RequestMapping("skill")//es como un prefijo de la url para esta, /user/* es algo de esta api
@CrossOrigin(origins = "https://my-portfolio-6385d.web.app")
public class SkillController {
    @Autowired//Annotation que se encarga de inicializar lo de abajo
    private SkillServiceImpl skillService;//servicio que maneja la bd de usuarios
    
    @Autowired
    private UserController userController;
    
    @GetMapping("{username}")
    public List<Skill> getSkillByUsername(@PathVariable(value = "username") String username){
        return skillService.findSkillByUsername(username);
    }
    
    @GetMapping
    public Skill getSkillById(@RequestParam Long id){
        return skillService.findSkillById(id);
    }
    
    @PostMapping
    public ResponseEntity<String> saveSkill(@RequestBody Skill skill, @RequestParam String username){
        UserEntity user = userController.getUser(username);
        skill.setUserEntity(user);
        skillService.saveSkill(skill);
        return new ResponseEntity<>("Save succesful", HttpStatus.OK);
    }

    
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable(value = "id") Long id){
        skillService.deleteSkillById(id);
        return new ResponseEntity<>("Delete succesful", HttpStatus.OK);
    }
}
