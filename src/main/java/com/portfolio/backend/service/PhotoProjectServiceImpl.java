/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.dto.PhotoProjectDto;
import com.portfolio.backend.model.PhotoProject;
import com.portfolio.backend.repository.PhotoProjectRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Maximiliano Borrajo
 */
@Service
public class PhotoProjectServiceImpl implements PhotoProjectService{ 
    
    @Autowired
    private PhotoProjectRepository photoRepo;

    @Override
    public PhotoProject findPhotoById(Long id) {
        return photoRepo.findById(id).orElse(null);
    }

    @Override
    public void savePhoto(PhotoProject photoProject) {
        photoRepo.save(photoProject);
    }

    @Override
    public void deletePhotoById(Long id) {
        photoRepo.deleteById(id);
    }

    @Override
    public List<PhotoProjectDto> findPhotosByProjectId(Long id) {
        List<PhotoProject> photos = photoRepo.findAll();
        List<PhotoProjectDto> photosProject = new ArrayList<>();
        for(PhotoProject photo:photos){
            if (Objects.equals(photo.getProject().getId_proyecto(), id)) {
                PhotoProjectDto dto = new PhotoProjectDto();
                dto.setId_photoProject(photo.getId_photoProject());
                dto.setLink(photo.getLink());
                dto.setName(photo.getName());
                dto.setId_proyecto(id);
                photosProject.add(dto);
            }
        }
        return photosProject;
    }
    
}
