package com.hetero.repository;


import com.hetero.models.Brand;
import com.hetero.models.BrandCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandCategoryDao extends JpaRepository<BrandCategory, Integer> {
    List<BrandCategory> findByBrand(Brand brand);
}
