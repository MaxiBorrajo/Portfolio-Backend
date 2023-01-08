/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.controller;

import com.portfolio.backend.model.InfoUser;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.service.InfoUserServiceImpl;
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
@RequestMapping("info")//es como un prefijo de la url para esta, /user/* es algo de esta api
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "true",
exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"} )
public class InfoUserController {
    @Autowired//Annotation que se encarga de inicializar lo de abajo
    private InfoUserServiceImpl infoService;//servicio que maneja la bd de usuarios
    
    @Autowired
    private UserController userController;
    
    @GetMapping
    public InfoUser getInfoUserById(@RequestParam Long id){
        return infoService.findInfoUserById(id);
    }
    
    @GetMapping("{username}")
    public InfoUser getInfoUserByUsername(@PathVariable(value = "username")
    String username){
        InfoUser infoUser = infoService.findInfoUserByUsername(username);
        return infoUser;
    }
    
    @PostMapping
    public ResponseEntity<String> saveInfoUser(@RequestBody InfoUser infoUser, @RequestParam String username){
        UserEntity user = userController.getUser(username);
        infoUser.setUserEntity(user);
        infoService.saveInfoUser(infoUser);
        return new ResponseEntity<>("Save succesful", HttpStatus.OK);
    }
    
    
    @DeleteMapping("{username}")
    public ResponseEntity<String> deleteInfoUser(@PathVariable(value = "username") String username){
        infoService.deleteInfoUserByUsername(username);
        return new ResponseEntity<>("Delete succesful", HttpStatus.OK);
    }
    /*
    @PostMapping("/upload")
    public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file,
            @RequestParam("username") String username){
        InfoUser infoUser = this.getInfoUserByUsername(username);
        Map<String, Object> response = new HashMap<>();
        if(!file.isEmpty()){
            String filename = file.getOriginalFilename();
            Path pathfile = Paths.get("uploads/infoUser").resolve(filename).toAbsolutePath();
            
            try {
                Files.copy(file.getInputStream(), pathfile);
            } catch (IOException ex) {
                response.put("mensaje", "Error al subir imagen");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        infoUser.setPhoto(filename);
        infoService.saveInfoUser(infoUser);
        
        response.put("mensaje", filename + " subido correctamente");
        }
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<?> deletePhoto(@PathVariable(value = "filename") String filename, 
            @RequestParam("username") String username){
        Path pathfile = Paths.get("uploads/infoUser").resolve(filename).toAbsolutePath();
        InfoUser infoUser = this.getInfoUserByUsername(username);
        try {
            boolean delete = Files.deleteIfExists(pathfile);
            infoUser.setPhoto(null);
        } catch (IOException ex) {
            Logger.getLogger(InfoUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK); 
    }*/
}
