/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.controller;

import com.portfolio.backend.model.Role;
import com.portfolio.backend.service.RoleServiceImpl;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author usuario
 */
@RestController
@RequestMapping("role")
@CrossOrigin(origins="*")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;
    
    @PostMapping("newRole")
    public ResponseEntity<String> saveRole(@RequestBody Role role){
        roleService.saveRole(role);
        return new ResponseEntity("New role created", HttpStatus.OK);
    }
    
    @GetMapping("{name}")
    public Role getRole(@PathVariable(value = "name") String name){
        return roleService.getRoleByName(name);
    }
    
    @DeleteMapping("{name}")//metodo delete y url
    public void deleteRole(@PathVariable(value = "name") String name){
        /*metodo que elimina al usuario dado en la url de la bd*/
        roleService.deleteRoleByName(name);
    }
}
