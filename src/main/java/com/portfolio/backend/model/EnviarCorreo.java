/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.model;

import lombok.Data;

/**
 *
 * @author usuario
 */
@Data
public class EnviarCorreo {
    String name;
    String fromEmail;
    String subject;
    String body;
}
