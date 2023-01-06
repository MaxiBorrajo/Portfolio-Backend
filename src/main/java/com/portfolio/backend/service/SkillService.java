/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.model.Skill;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface SkillService {
    public Skill findSkillById(Long id);
    public List<Skill> findSkillByUsername(String username);
    public void saveSkill(Skill skill);
    public void deleteSkillById(Long id);
}
