/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.repository;

import com.portfolio.backend.model.Skill;
import com.portfolio.backend.model.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author usuario
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{

    /**
     *
     * @param userEntity
     * @return
     */
    Optional<List<Skill>> findByUserEntity(UserEntity userEntity);
}
