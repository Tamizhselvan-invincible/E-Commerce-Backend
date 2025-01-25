package com.hetero.controller;

import com.hetero.models.Brand;
import com.hetero.models.BrandCategory;
import com.hetero.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Integer brandId) {
        return ResponseEntity.ok(brandService.getBrandById(brandId));
    }

    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand, @RequestParam List<Integer> categoryIds) {
        return new ResponseEntity<>(brandService.createBrand(brand, categoryIds), HttpStatus.CREATED);
    }

    @PutMapping("/{brandId}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Integer brandId, @RequestBody Brand brand) {
        brand.setId(brandId);
        return ResponseEntity.ok(brandService.updateBrand(brand));
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer brandId) {
        brandService.deleteBrand(brandId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{brandId}/categories")
    public ResponseEntity<List<BrandCategory>> getCategoriesOfSpecificBrand(@PathVariable Integer brandId) {
        return ResponseEntity.ok(brandService.getCategoriesOfSpecificBrand(brandId));
    }
}
