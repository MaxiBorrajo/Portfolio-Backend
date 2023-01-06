/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.Role;
import com.portfolio.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maximiliano Borrajo
 */
@Service// Indica que es un servicio 
public class RoleServiceImpl implements RoleService{

    @Autowired//Annotation que se encarga de inicializar lo de abajo
    private RoleRepository roleRepo;//repositorio de los roles
    
    @Override
    public void saveRole(Role role) {
        //guarda un rol en la bd
        roleRepo.save(role);
    }

    @Override
    public Role getRoleByName(String name) {
        //devuelve el rol dado
        Role role = roleRepo.findByName(name).orElse(null);
        return role;
    }

    @Override
    public void deleteRoleByName(String name) {
        //elimina el rol dado
        Role role = roleRepo.findByName(name).orElse(null);
        if(role != null){
            roleRepo.deleteById(role.getId_role());
        }
    }
    
}
