/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.Skill;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.repository.SkillRepository;
import com.portfolio.backend.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class SkillServiceImpl implements SkillService{
    @Autowired
    private SkillRepository skillRepo;
    
    @Autowired
    private UserRepository userRepo;

    @Override
    public Skill findSkillById(Long id) {
        return skillRepo.findById(id).orElse(null);
    }

    @Override
    public List<Skill> findSkillByUsername(String username) {
        UserEntity user = userRepo.findByUserName(username).orElse(null);
        if(user != null){
            return skillRepo.findByUserEntity(user).orElse(null);
        }else{
            return null;
        }
    }

    @Override
    public void saveSkill(Skill skill) {
        skillRepo.save(skill);
    }

    @Override
    public void deleteSkillById(Long id) {
        skillRepo.deleteById(id);
    }
}
