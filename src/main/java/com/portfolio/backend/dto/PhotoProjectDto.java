/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.dto;

import lombok.Data;

/**
 *
 * @author Maximiliano Borrajo
 */
@Data
public class PhotoProjectDto {
    private Long id_photoProject;
    private String name;
    private String link;
    private Long id_proyecto;
}
