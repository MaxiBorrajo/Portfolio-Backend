/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.repository;

import com.portfolio.backend.model.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Maximiliano Borrajo
 */
@Repository //Annotation que indica que lo de abajo es un repositorio
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    //Extiende jpa repositorio para traer todos sus metodos
    Optional<UserEntity> findByUserName(String userName);
    //metodo opcional creado para hallar usuarios por su username
    boolean existsByUserName(String userName);
    //metodo que indica si existe en la bd un usuario con el username dado
}
