package com.hetero.repository;

import com.hetero.models.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeDao extends JpaRepository<ProductAttribute, Integer> {
}
