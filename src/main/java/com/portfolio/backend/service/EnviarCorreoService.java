/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class EnviarCorreoService {
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendEmail(String fromEmail,
                            String nombre,
                            String subject,
                            String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo("maximilianoborrajohernan@gmail.com");
        message.setText("From: " + nombre + "\n" + 
                        "Email: "+ fromEmail + "\n" +
                        body);
        message.setSubject(subject);
        mailSender.send(message);
    }
}
