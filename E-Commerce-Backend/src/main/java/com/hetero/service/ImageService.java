package com.hetero.service;


import com.hetero.models.Images;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface ImageService {
    Images uploadImage(MultipartFile file, String path, String imageName);
    String saveImageDetails(Images image);
    List<Images> fetchImages(String mediaCategory, int limit);
    List<Images> loadMoreImages(String mediaCategory, int limit, LocalDateTime lastFetchedDate);
    List<Images> fetchAllImages();
    void deleteImage(Long id);
}
