/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.controller;

import com.portfolio.backend.model.EducationUser;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.service.EducationUserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maximiliano Borrajo
 */
@RestController//annotation que indica que esto es una api rest
@RequestMapping("education")//es como un prefijo de la url para esta, /user/* es algo de esta api
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "true",
exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"} )
public class EducationController {
    @Autowired//Annotation que se encarga de inicializar lo de abajo
    private EducationUserServiceImpl eduService;//servicio que maneja la bd de usuarios
    
    @Autowired
    private UserController userController;
    
    @GetMapping("{username}")
    public List<EducationUser> getEducationUserByUsername(@PathVariable(value = "username") String username){
        return eduService.findEducationUserByUsername(username);
    }
    
    @GetMapping
    public EducationUser getEducationUserById(@RequestParam Long id){
        return eduService.findEducationUserById(id);
    }
    
    @PostMapping
    public ResponseEntity<String> saveEducationUser(@RequestBody EducationUser educationUser, @RequestParam String username){
        UserEntity user = userController.getUser(username);
        educationUser.setUserEntity(user);
        eduService.saveEducationUser(educationUser);
        return new ResponseEntity<>("Save succesful", HttpStatus.OK);
    }

    
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEducationUser(@PathVariable(value = "id") Long id){
        eduService.deleteEducationUserById(id);
        return new ResponseEntity<>("Delete succesful", HttpStatus.OK);
    }
    /*
    @PostMapping("/upload")
    public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file,
            @RequestParam("id") Long id){
        EducationUser eduUser = this.getEducationUserById(id);
        Map<String, Object> response = new HashMap<>();
        if(!file.isEmpty()){
            String filename = file.getOriginalFilename();
            Path pathfile = Paths.get("uploads/eduUser").resolve(filename).toAbsolutePath();
            
            try {
                if(Files.notExists(pathfile)){
                    Files.copy(file.getInputStream(), pathfile);
                }
            } catch (IOException ex) {
                response.put("mensaje", "Error al subir imagen");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        eduUser.setInst_brand(filename);
        eduService.saveEducationUser(eduUser);
        
        response.put("mensaje", filename + " subido correctamente");
        }
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<?> deletePhoto(@PathVariable(value = "filename") String filename,
            @RequestParam("username") String username,
            @RequestParam("id") Long id){
        Path pathfile = Paths.get("uploads/eduUser").resolve(filename).toAbsolutePath();
        List<EducationUser> listEduUser = this.getEducationUserByUsername(username);
        EducationUser eduUser = this.getEducationUserById(id);
        try {
            int cantUsosFoto = 0;
            for(EducationUser edu :  listEduUser){
                if(edu.getInst_brand().equals(filename)){
                    ++cantUsosFoto;
                }
            }
            if(cantUsosFoto > 1){
                eduUser.setInst_brand(null);
            }else{
                eduUser.setInst_brand(null);
                boolean delete = Files.deleteIfExists(pathfile);
            }
        } catch (IOException ex) {
            Logger.getLogger(InfoUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK); 
    }*/
}
