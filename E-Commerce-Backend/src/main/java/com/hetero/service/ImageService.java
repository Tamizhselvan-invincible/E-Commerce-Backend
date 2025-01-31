package com.hetero.service;


import com.hetero.models.Images;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ImageService {
    Images uploadImage(MultipartFile file, String path, String imageName) throws Exception;
    String saveImageToDatabase(Images image);

    Page<Images> fetchImages(String mediaCategory, Pageable pageable);
    Page<Images> loadMoreImages(String mediaCategory, Pageable pageable, LocalDateTime lastFetchedDate);
    List<Images> fetchAllImages();
    void deleteImage(String imageId) throws Exception;
}