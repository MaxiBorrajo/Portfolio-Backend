/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.InfoUser;
import com.portfolio.backend.model.UserEntity;
import java.util.List;

/**
 *
 * @author Maximiliano Borrajo
 */
public interface InfoUserService {
    public InfoUser findInfoUserById(Long id);
    public InfoUser findInfoUserByUser(UserEntity user);
    public void saveInfoUser(InfoUser infoUser);
    public void deleteInfoUserByUsername(String username);
}
