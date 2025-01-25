package com.hetero.service;

import com.hetero.models.Brand;
import com.hetero.models.BrandCategory;
import com.hetero.models.Category;
import com.hetero.repository.BrandCategoryDao;
import com.hetero.repository.BrandDao;
import com.hetero.repository.CategoryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandDao brandDao;
    private final BrandCategoryDao brandCategoryDao;
    private final CategoryDao categoryDao;

    @Override
    public List<Brand> getAllBrands() {
        return brandDao.findAll();
    }

    @Override
    public Brand getBrandById(Integer brandId) {
        return brandDao.findById(brandId)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    @Override
    @Transactional
    public Brand createBrand(Brand brand, List<Integer> categoryIds) {
        List<Category> categories = categoryDao.findAllById(categoryIds);
        if (categories.isEmpty()) {
            throw new RuntimeException("No valid categories provided");
        }
        brand = brandDao.save(brand);
        for (Category category : categories) {
            BrandCategory brandCategory = new BrandCategory();
            brandCategory.setBrand(brand);
            brandCategory.setCategory(category);
            brandCategoryDao.save(brandCategory);
        }
        return brand;
    }

    @Override
    public Brand updateBrand(Brand brand) {
        if (!brandDao.existsById(brand.getId())) {
            throw new RuntimeException("Brand not found");
        }
        return brandDao.save(brand);
    }

    @Override
    @Transactional
    public void deleteBrand(Integer brandId) {
        Brand brand = getBrandById(brandId);
        brandCategoryDao.deleteAll(brand.getBrandCategories());
        brandDao.delete(brand);
    }

    @Override
    public List<BrandCategory> getCategoriesOfSpecificBrand(Integer brandId) {
        Brand brand = getBrandById(brandId);
        return brandCategoryDao.findByBrand(brand);
    }
}
