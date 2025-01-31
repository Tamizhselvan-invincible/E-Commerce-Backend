package com.hetero.service;


import com.hetero.models.Images;
import com.hetero.repository.ImageDao;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.nio.file.*;
        import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageDao imageRepository;

    @Value("${image.storage.path}")
    private String storagePath;

    public ImageServiceImpl(ImageDao imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Images uploadImage(MultipartFile file, String path, String imageName) {
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path destination = Paths.get(storagePath + "/" + path + "/" + fileName);
            Files.createDirectories(destination.getParent());
            Files.write(destination, file.getBytes(), StandardOpenOption.CREATE);

            Images image = new Images();
            image.setUrl(destination.toUri().toString());
            image.setFolder(path);
            image.setFilename(fileName);
            image.setSizeBytes(file.getSize());
            image.setFullPath(destination.toString());
            image.setContentType(file.getContentType());
            image.setCreatedAt(LocalDateTime.now());

            return imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file: " + e.getMessage());
        }
    }

    @Override
    public String saveImageDetails(Images image) {
        return String.valueOf(imageRepository.save(image).getId());
    }

    @Override
    public List<Images> fetchImages(String mediaCategory, int limit) {
        return imageRepository.findByMediaCategoryOrderByCreatedAtDesc(mediaCategory, limit);
    }

    @Override
    public List<Images> loadMoreImages (String mediaCategory, int limit, LocalDateTime lastFetchedDate) {
        return imageRepository.findByMediaCategoryAndCreatedAtBeforeOrderByCreatedAtDesc(mediaCategory, lastFetchedDate, limit);
    }



    @Override
    public List<Images> fetchAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public void deleteImage(Long id) {
        Optional<Images> image = imageRepository.findById(id);
        if (image.isPresent()) {
            try {
                Files.deleteIfExists(Paths.get(image.get().getFullPath()));
                imageRepository.deleteById(id);
            } catch (IOException e) {
                throw new RuntimeException("Error deleting file: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("Image not found with id: " + id);
        }
    }
}
