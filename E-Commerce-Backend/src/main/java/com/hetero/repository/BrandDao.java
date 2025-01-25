package com.hetero.repository;

import com.hetero.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer> {

    List<Brand> findByIsFeatured(boolean isFeatured);
}
