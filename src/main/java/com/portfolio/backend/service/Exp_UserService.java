/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.Exp_User;
import com.portfolio.backend.model.UserEntity;
import java.util.List;

/**
 *
 * @author Maximiliano Borrajo
 */
public interface Exp_UserService {
    public Exp_User findExpUserById(Long id);
    public List<Exp_User> findExpUserByUser(UserEntity user);
    public void saveExpUser(Exp_User expUser);
    public void deleteExpUserById(Long id);
}
