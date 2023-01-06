/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author Maximiliano Borrajo
 */
@Entity //Lo guarda como tabla en la bd, toma los atributos como columnas
@Data //annotation que evita escribir getters y setters
public class PhotoProject {
    @Id //annotation del pk de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)//la forma en que se va a ir estableciendo los pk
    private Long id_photoProject;
    private String name;
    private String link;
    
    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private Project project;
}
