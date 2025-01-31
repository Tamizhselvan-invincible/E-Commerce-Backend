package com.hetero.controller;

import com.hetero.exception.BannerNotFoundException;
import com.hetero.models.Banner;
import com.hetero.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banners")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @PostMapping
    public ResponseEntity<Banner> createBanner(@RequestBody Banner banner) {
        Banner createdBanner = bannerService.createBanner(banner);
        return ResponseEntity.ok(createdBanner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Banner> updateBanner(@PathVariable int id, @RequestBody Banner banner) {
        Banner updatedBanner = bannerService.updateBanner(id, banner);
        return ResponseEntity.ok(updatedBanner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable int id) {
        bannerService.deleteBanner(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Banner> getBannerById(@PathVariable int id) {
        Banner banner = bannerService.getBannerById(id);
        return ResponseEntity.ok(banner);
    }

    @GetMapping
    public ResponseEntity<List<Banner>> getAllBanners() {
        List<Banner> banners = bannerService.getAllBanners();
        return ResponseEntity.ok(banners);
    }
}
