/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.Role;

/**
 *
 * @author Maximiliano Borrajo
 */
public interface RoleService {
    public void saveRole(Role role);
    
    public Role getRoleByName(String name);
    
    public void deleteRoleByName(String name);
}
