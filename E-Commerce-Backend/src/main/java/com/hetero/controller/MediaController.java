package com.hetero.controller;


import com.hetero.models.Images;
import com.hetero.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Images> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("path") String path,
            @RequestParam("imageName") String imageName) {
        return ResponseEntity.ok(imageService.uploadImage(file, path, imageName));
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveImageDetails(@RequestBody Images image) {
        return ResponseEntity.ok(imageService.saveImageDetails(image));
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<Images>> fetchImages(
            @RequestParam("mediaCategory") String mediaCategory,
            @RequestParam("limit") int limit) {
        return ResponseEntity.ok(imageService.fetchImages(mediaCategory, limit));
    }

    @GetMapping("/loadMore")
    public ResponseEntity<List<Images>> loadMoreImages(
            @RequestParam("mediaCategory") String mediaCategory,
            @RequestParam("limit") int limit,
            @RequestParam("lastFetchedDate") LocalDateTime lastFetchedDate) {
        return ResponseEntity.ok(imageService.loadMoreImages(mediaCategory, limit, lastFetchedDate));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Images>> fetchAllImages() {
        return ResponseEntity.ok(imageService.fetchAllImages());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok("Image deleted successfully");
    }
}
