package com.hetero.repository;

import com.hetero.models.ProductVariation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariationDao extends JpaRepository<ProductVariation, Integer> {
}
