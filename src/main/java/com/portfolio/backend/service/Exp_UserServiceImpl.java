/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.Exp_User;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.repository.ExpUserRepository;
import com.portfolio.backend.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maximiliano Borrajo
 */
@Service
public class Exp_UserServiceImpl implements Exp_UserService{

    @Autowired
    private ExpUserRepository expRepo;

    @Override
    public Exp_User findExpUserById(Long id) {
        return expRepo.findById(id).orElse(null);
    }

    @Override
    public void saveExpUser(Exp_User expUser) {
       expRepo.save(expUser);
    }

    @Override
    public void deleteExpUserById(Long id) {
        expRepo.deleteById(id);
    }

    @Override
    public List<Exp_User> findExpUserByUser(UserEntity user) {
        return expRepo.findByUserEntity(user).orElse(null);
    }
    
}
