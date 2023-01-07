/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.controller;

import com.portfolio.backend.model.EnviarCorreo;
import com.portfolio.backend.service.EnviarCorreoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author usuario
 */
@RestController
@RequestMapping("contacto")
@CrossOrigin(origins = "https://my-portfolio-6385d.web.app")
public class EnviarCorreoController {
    @Autowired
    EnviarCorreoService correoService;
    
    @PostMapping("enviar")
    public ResponseEntity<String> enviarCorreo(@RequestBody EnviarCorreo correo){
        correoService.sendEmail(correo.getFromEmail(), correo.getName(),
                correo.getSubject(), correo.getBody());
        return new ResponseEntity<>("Mail sent correctly", HttpStatus.OK);
    }
}
