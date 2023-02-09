/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.controller;

import com.portfolio.backend.dto.PhotoProjectDto;
import com.portfolio.backend.model.PhotoProject;
import com.portfolio.backend.model.Project;
import com.portfolio.backend.model.UserEntity;
import com.portfolio.backend.service.ImageService;
import com.portfolio.backend.service.PhotoProjectServiceImpl;
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
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("photo")//es como un prefijo de la url para esta, /user/* es algo de esta api
@CrossOrigin(origins="*")
public class PhotoProjectController {
    @Autowired//Annotation que se encarga de inicializar lo de abajo
    private PhotoProjectServiceImpl photoService;//servicio que maneja la bd de usuarios
    
    @Autowired
    private ProjectController projectController;
    
    @Autowired
    private ImageService imgService;
    
    @GetMapping
    public PhotoProject getPhotoById(@RequestParam Long id){
        return photoService.findPhotoById(id);
    }
    
    @GetMapping("photos-of-project")
    public List<PhotoProjectDto> getPhotosByIdProject(@RequestParam Long id){
        return photoService.findPhotosByProjectId(id);
    }
    
    @PostMapping
    public ResponseEntity<String> savePhotoEntity(@RequestParam Long id_proyecto,
            @RequestParam MultipartFile multipartFile) throws IOException, Exception{
        Project project = projectController.getProjectById(id_proyecto);
        PhotoProject photoProject = new PhotoProject();
        photoProject.setProject(project);
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        Map result = imgService.upload(multipartFile);
        photoProject.setLink((String) result.get("url"));
        photoProject.setName((String) result.get("public_id"));
        photoService.savePhoto(photoProject);
        return new ResponseEntity<>("Save succesful", HttpStatus.OK);
    }
    
    
    @DeleteMapping
    public ResponseEntity<String> deletePhoto(@RequestParam Long id) throws IOException{
        PhotoProject photoProject = photoService.findPhotoById(id);
        Map result = imgService.delete(photoProject.getName());
        photoService.deletePhotoById(id);
        return new ResponseEntity<>("Delete succesful", HttpStatus.OK);
    }
}
