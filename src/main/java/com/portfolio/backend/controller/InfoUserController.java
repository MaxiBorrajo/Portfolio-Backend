/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.controller;

import com.portfolio.backend.model.InfoUser;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.service.ImageService;
import com.portfolio.backend.service.InfoUserServiceImpl;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Maximiliano Borrajo
 */
@RestController//annotation que indica que esto es una api rest
@RequestMapping("info")//es como un prefijo de la url para esta, /user/* es algo de esta api
@CrossOrigin(origins="*")
public class InfoUserController {
    @Autowired//Annotation que se encarga de inicializar lo de abajo
    private InfoUserServiceImpl infoService;//servicio que maneja la bd de usuarios
    
    @Autowired
    private UserController userController;
    
    @Autowired
    private ImageService imgService;
    
    @GetMapping
    public InfoUser getInfoUserById(@RequestParam Long id){
        return infoService.findInfoUserById(id);
    }
    
    @GetMapping("{username}")
    public InfoUser getInfoUserByUsername(@PathVariable(value = "username")
    String username){
        UserEntity user = userController.getUser(username);
        return infoService.findInfoUserByUser(user);
    }
    
    @PostMapping
    public ResponseEntity<String> saveInfoUser(@RequestBody InfoUser infoUser, @RequestParam String username) throws IOException, Exception{
        UserEntity user = userController.getUser(username);
        infoUser.setUserEntity(user);
        infoService.saveInfoUser(infoUser);
        return new ResponseEntity<>("Save succesful", HttpStatus.OK);
    }
    
    @PostMapping("photo")
    public ResponseEntity<String> saveInfoUserPhoto(@RequestParam MultipartFile multipartFile, @RequestParam String username) throws IOException, Exception{
        UserEntity user = userController.getUser(username);
        InfoUser infoUser = infoService.findInfoUserByUser(user);
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        Map result = imgService.upload(multipartFile);
        infoUser.setPhoto((String) result.get("url"));
        infoUser.setCloud_id((String) result.get("public_id"));
        infoService.saveInfoUser(infoUser);
        return new ResponseEntity<>("Save succesful", HttpStatus.OK);
    }
    
    
    @DeleteMapping("{username}")
    public ResponseEntity<String> deleteInfoUser(@PathVariable(value = "username") String username) throws IOException{
        UserEntity user = userController.getUser(username);
        InfoUser infoUser = infoService.findInfoUserByUser(user);
        Map result = imgService.delete(infoUser.getCloud_id());
        infoService.deleteInfoUserByUsername(username);
        return new ResponseEntity<>("Delete succesful", HttpStatus.OK);
    }
    
    @DeleteMapping("photo/{username}")
    public ResponseEntity<String> deletePhotoInfoUser(@PathVariable(value = "username") String username) throws IOException{
        UserEntity user = userController.getUser(username);
        InfoUser infoUser = infoService.findInfoUserByUser(user);
        Map result = imgService.delete(infoUser.getCloud_id());
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
