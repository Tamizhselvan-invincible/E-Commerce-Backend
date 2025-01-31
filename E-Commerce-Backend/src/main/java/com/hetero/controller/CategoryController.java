package com.hetero.controller;

import com.hetero.models.Category;
import com.hetero.service.CategoryService;
import com.hetero.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(savedCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Integer id,
            @RequestBody Category updatedCategory) {
        if (!categoryService.getCategoryById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        updatedCategory.setCategoryId(id);
        Category savedCategory = categoryService.saveCategory(updatedCategory);
        return ResponseEntity.ok(savedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        if (!categoryService.getCategoryById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
