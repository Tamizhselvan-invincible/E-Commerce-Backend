package com.hetero.repository;

import com.hetero.models.ProductVariation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariationDao extends JpaRepository<ProductVariation, Integer> {
}
