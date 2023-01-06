/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maximiliano Borrajo
 */

@Service// Indica que es un servicio 
public class UserEntityServiceImpl implements UserEntityService{

    @Autowired//Annotation que se encarga de inicializar lo de abajo
    private UserRepository userRepo;//repositorio de los roles
    
    @Override
    public void saveUser(UserEntity user) {
        /*Se encarga de guardar la usuarios en la bd*/
        userRepo.save(user);
    }

    @Override
    public UserEntity getUserByUsername(String userName) {
        /*describe el usuario dado*/
        return userRepo.findByUserName(userName).orElse(null);
    }

    @Override
    public void deleteUserByUsername(String userName) {
        /*elimina el usuario dado*/
        UserEntity user = userRepo.findByUserName(userName).orElse(null);
        if(user != null){
            userRepo.deleteById(user.getId_user());
        }
    }

    @Override
    public boolean existsByUsername(String userName) {
        /*Indica si el usuario dado existe en la bd*/
        return userRepo.existsByUserName(userName);
    }
    
}
