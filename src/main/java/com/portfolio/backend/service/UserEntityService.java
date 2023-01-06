/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.UserEntity;

/**
 *
 * @author Maximiliano Borrajo
 */
public interface UserEntityService {
    
    public void saveUser(UserEntity user);
    
    public UserEntity getUserByUsername(String userName);
    
    public void deleteUserByUsername(String userName);
    
    public boolean existsByUsername(String userName);
}
