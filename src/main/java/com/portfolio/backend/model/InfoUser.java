/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Clob;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.Data;

/**
 *
 * @author Maximiliano Borrajo
 */
@Entity //Lo guarda como tabla en la bd, toma los atributos como columnas
@Data //annotation que evita escribir getters y setters
public class InfoUser {
    @Id //annotation del pk de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)//la forma en que se va a ir estableciendo los pk
    private Long id_InfoUser;
    private String name;
    private String last_name;
    private String photo;
    private String cloud_id;
    private String title;
    @Column(name="description", length = 100000)
    @Lob
    private String description;
    
    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @JsonIgnore
    private UserEntity userEntity;
}
