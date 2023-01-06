/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author Maximiliano Borrajo
 */
@Entity //Lo guarda como tabla en la bd, toma los atributos como columnas
@Data //annotation que evita escribir getters y setters
public class EducationUser {
    @Id //annotation del pk de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)//la forma en que se va a ir estableciendo los pk
    private Long id_EducationUser;
    private String title;
    private String institution;
    private String inst_brand;
    private String period;
    @ManyToOne(optional = false,
            fetch = FetchType.EAGER)
    private UserEntity userEntity;
}
