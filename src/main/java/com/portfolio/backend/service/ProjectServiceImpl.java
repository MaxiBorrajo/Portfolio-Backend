/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.Project;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.repository.ProjectRepository;
import com.portfolio.backend.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maximiliano Borrajo
 */
@Service
public class ProjectServiceImpl implements ProjectService{
    
    @Autowired
    private ProjectRepository projectRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Override
    public Project findProjectById(Long id) {
        return projectRepo.findById(id).orElse(null);
    }

    @Override
    public List<Project> findProjectByUsername(String username) {
        UserEntity user = userRepo.findByUserName(username).orElse(null);
        if(user != null){
            return projectRepo.findByUserEntity(user).orElse(null);
        }else{
            return null;
        }
    }

    @Override
    public void saveProject(Project project) {
        projectRepo.save(project);
    }

    @Override
    public void deleteProjectById(Long id) {
        projectRepo.deleteById(id);
    }
    
}
