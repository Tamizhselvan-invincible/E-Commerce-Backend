package com.hetero.service;

import com.hetero.models.Category;
import com.hetero.repository.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl {

    private final CategoryDao categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Create or Update a category
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Retrieve all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Retrieve a category by ID
    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    // Delete a category by ID
    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
