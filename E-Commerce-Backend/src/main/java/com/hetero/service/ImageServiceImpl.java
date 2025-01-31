package com.hetero.service;


import com.hetero.models.Images;
import com.hetero.repository.ImageDao;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.data.domain.Page;


@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageDao imageDao;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public Images uploadImage(MultipartFile file, String path, String imageName) throws Exception {
        Path uploadPath = Paths.get(uploadDir + "/" + path);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(imageName);
        Files.copy(file.getInputStream(), filePath);

        Images image = new Images();
        image.setUrl("/api/images/" + path + "/" + imageName);
        image.setFolder(path);
        image.setFilename(imageName);
        image.setSizeBytes(file.getSize());
        image.setFullPath(path + "/" + imageName);
        image.setCreatedAt(LocalDateTime.now());
        image.setContentType(file.getContentType());

        return image;
    }

    @Override
    public String saveImageToDatabase(Images image) {
        Images savedImage = imageDao.save(image);
        return savedImage.getId();
    }

    @Override
    public Page<Images> fetchImages(String mediaCategory, Pageable pageable) {
        return imageDao.findByMediaCategoryOrderByCreatedAtDesc(mediaCategory, pageable);
    }

    @Override
    public Page<Images> loadMoreImages(String mediaCategory, Pageable pageable, LocalDateTime lastFetchedDate) {
        return imageDao.findByMediaCategoryAndCreatedAtLessThanOrderByCreatedAtDesc(
                mediaCategory, lastFetchedDate, pageable);
    }

    @Override
    public List<Images> fetchAllImages() {
        return imageDao.findAll();
    }

    @Override
    public void deleteImage(String imageId) throws Exception {
        Images image = imageDao.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        Path filePath = Paths.get(uploadDir + "/" + image.getFullPath());
        Files.deleteIfExists(filePath);
        imageDao.deleteById(imageId);
    }
}