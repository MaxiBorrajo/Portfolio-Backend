/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.EducationUser;
import com.portfolio.backend.model.UserEntity;
import java.util.List;

/**
 *
 * @author Maximiliano Borrajo
 */
public interface EducationUserService {
    public EducationUser findEducationUserById(Long id);
    public List<EducationUser> findEducationUserByUser(UserEntity user);
    public void saveEducationUser(EducationUser educationUser);
    public void deleteEducationUserById(Long id);
}
