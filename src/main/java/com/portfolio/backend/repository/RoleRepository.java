/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.repository;

import com.portfolio.backend.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Maximiliano Borrajo
 */
@Repository //Annotation que indica que lo de abajo es un repositorio
public interface RoleRepository extends JpaRepository<Role, Long>{
    //Extiende jpa repositorio para traer todos sus metodos
    Optional<Role> findByName(String name);
    //metodo opcional creado para hallar roles por su nombre
}
