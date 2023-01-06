/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.InfoUser;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.repository.InfoUserRepository;
import com.portfolio.backend.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maximiliano Borrajo
 */
@Service
public class InfoUserServiceImpl implements InfoUserService{
    @Autowired
    private InfoUserRepository infoRepo;
    
    @Autowired
    private UserRepository userRepo;

    @Override
    public InfoUser findInfoUserById(Long id) {
        return infoRepo.findById(id).orElse(null);
    }

    @Override
    public InfoUser findInfoUserByUsername(String username) {
        UserEntity user = userRepo.findByUserName(username).orElse(null);
        if(user != null){
            return infoRepo.findByUserEntity(user).orElse(null);
        }else{
            return null;
        }
    }

    @Override
    public void saveInfoUser(InfoUser infoUser) {
        infoRepo.save(infoUser);
    }

    @Override
    public void deleteInfoUserByUsername(String username) {
        UserEntity user = userRepo.findByUserName(username).orElse(null);
        if(user != null && user.getInfoUser() != null){
            infoRepo.deleteById(user.getInfoUser().getId_InfoUser());
        }
    }
}
