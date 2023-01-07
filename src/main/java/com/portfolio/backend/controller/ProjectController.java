/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.controller;

import com.portfolio.backend.model.PhotoProject;
import com.portfolio.backend.model.Project;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.service.ProjectServiceImpl;
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
@RequestMapping("project")//es como un prefijo de la url para esta, /user/* es algo de esta api
@CrossOrigin(origins = "https://my-portfolio-6385d.web.app")
public class ProjectController {
    @Autowired//Annotation que se encarga de inicializar lo de abajo
    private ProjectServiceImpl projectService;//servicio que maneja la bd de usuarios
    
    @Autowired
    private UserController userController;
    
    @GetMapping("{username}")
    public List<Project> getProjectsByUsername(@PathVariable(value = "username") String username){
        return projectService.findProjectByUsername(username);
    }
    
    @GetMapping
    public Project getProjectById(@RequestParam Long id){
        return projectService.findProjectById(id);
    }
    
    
    @PostMapping
    public ResponseEntity<String> saveProject(@RequestBody Project project, @RequestParam String username){
        UserEntity user = userController.getUser(username);
        project.setUserEntity(user);
        projectService.saveProject(project);
        return new ResponseEntity<>("Save succesful", HttpStatus.OK);
    }
    
    
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable(value = "id") Long id){
        projectService.deleteProjectById(id);
        return new ResponseEntity<>("Delete succesful", HttpStatus.OK);
    }
}
