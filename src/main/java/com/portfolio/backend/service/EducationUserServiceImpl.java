/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.EducationUser;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.repository.EducationUserRepository;
import com.portfolio.backend.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maximiliano Borrajo
 */
@Service
public class EducationUserServiceImpl implements EducationUserService{
    
    @Autowired
    private EducationUserRepository eduRepo;
    
    @Override
    public EducationUser findEducationUserById(Long id) {
       return eduRepo.findById(id).orElse(null);
    }
    
    @Override
    public List<EducationUser> findEducationUserByUser(UserEntity user) {
        return eduRepo.findByUserEntity(user).orElse(null);
    }

    @Override
    public void saveEducationUser(EducationUser educationUser) {
        eduRepo.save(educationUser);
    }

    @Override
    public void deleteEducationUserById(Long id) {
        eduRepo.deleteById(id);
    }

    
    
}
