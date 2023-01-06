/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.Data;

/**
 *
 * @author Maximiliano Borrajo
 */
@Entity //Lo guarda como tabla en la bd, toma los atributos como columnas
@Data //annotation que evita escribir getters y setters
public class UserEntity {
    @Id //annotation del pk de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)//la forma en que se va a ir estableciendo los pk
    private Long id_user;//pk de la tabla
    private String userName;//nombre de usuario
    private String password;//contraseña del usuario
    private String email;//email del usuario
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    //fetch es la propiedad que se utiliza para determinar cómo debe ser cargada la entidad
    //Eager Indica que la relación debe de ser cargada al momento de cargar la entidad.
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user"), 
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id_role"))
    //join table indica hacia que tabla se da la relacion y con que
    private List<Role> roles = new ArrayList<>();//lista de roles del usuario
    
    
    @OneToOne(mappedBy = "userEntity")
    private InfoUser infoUser;
}
