package com.hetero.controller;


import com.hetero.models.Images;
import com.hetero.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/images")
public class MediaController  {
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("path") String path,
            @RequestParam("imageName") String imageName) {
        try {
            Images image = imageService.uploadImage(file, path, imageName);
            String imageId = imageService.saveImageToDatabase(image);
            return ResponseEntity.ok(imageId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<Images>> fetchImages(
            @RequestParam("mediaCategory") String mediaCategory,
            Pageable pageable) {
        return ResponseEntity.ok(imageService.fetchImages(mediaCategory, pageable));
    }

    @GetMapping("/more")
    public ResponseEntity<Page<Images>> loadMoreImages(
            @RequestParam("mediaCategory") String mediaCategory,
            @RequestParam("lastFetchedDate") LocalDateTime lastFetchedDate,
            Pageable pageable) {
        return ResponseEntity.ok(imageService.loadMoreImages(mediaCategory, pageable, lastFetchedDate));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Images>> fetchAllImages() {
        return ResponseEntity.ok(imageService.fetchAllImages());
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable String imageId) {
        try {
            imageService.deleteImage(imageId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
