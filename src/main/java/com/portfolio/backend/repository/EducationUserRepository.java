/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.repository;

import com.portfolio.backend.model.EducationUser;
import com.portfolio.backend.model.Project;
import com.portfolio.backend.model.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Maximiliano Borrajo
 */
@Repository
public interface EducationUserRepository extends JpaRepository<EducationUser, Long>{
    Optional<List<EducationUser>> findByUserEntity(UserEntity userEntity);
}
