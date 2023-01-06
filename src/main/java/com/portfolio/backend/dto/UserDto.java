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
@Data //annotation que evita escribir getters y setters
public class UserDto { //clase creada con el fin de transmitir datos, en este caso, para el login
    
    private String userName;//nombre de usuario
    private String password;//contraseña del usuario
}
