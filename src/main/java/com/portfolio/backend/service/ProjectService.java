/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.Project;
import java.util.List;

/**
 *
 * @author Maximiliano Borrajo
 */
public interface ProjectService {
    public Project findProjectById(Long id);
    public List<Project> findProjectByUsername(String username);
    public void saveProject(Project project);
    public void deleteProjectById(Long id);
}
