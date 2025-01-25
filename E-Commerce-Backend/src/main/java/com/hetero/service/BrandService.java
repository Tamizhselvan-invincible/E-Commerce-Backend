package com.hetero.service;

import com.hetero.models.Brand;
import com.hetero.models.BrandCategory;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrands();
    Brand getBrandById(Integer brandId);
    Brand createBrand(Brand brand, List<Integer> categoryIds);
    Brand updateBrand(Brand brand);
    void deleteBrand(Integer brandId);
    List<BrandCategory> getCategoriesOfSpecificBrand(Integer brandId);
}
