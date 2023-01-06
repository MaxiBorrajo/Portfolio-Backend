/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.backend.service;

import com.portfolio.backend.dto.PhotoProjectDto;
import com.portfolio.backend.model.PhotoProject;
import java.util.List;


/**
 *
 * @author Maximiliano Borrajo
 */
public interface PhotoProjectService {
    public PhotoProject findPhotoById(Long id);
    public List<PhotoProjectDto> findPhotosByProjectId(Long id);
    public void savePhoto(PhotoProject photoProject);
    public void deletePhotoById(Long id);
}
