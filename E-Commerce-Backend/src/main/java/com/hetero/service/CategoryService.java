package com.hetero.service;

import com.hetero.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    // Create or update a category
    Category saveCategory(Category category);

    // Retrieve all categories
    List<Category> getAllCategories();

    // Retrieve a category by ID
    Optional<Category> getCategoryById(Integer id);

    // Delete a category by ID
    void deleteCategoryById(Integer id);
}

