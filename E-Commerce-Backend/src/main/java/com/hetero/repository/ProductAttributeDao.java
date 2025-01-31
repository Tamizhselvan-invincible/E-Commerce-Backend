package com.hetero.repository;

import com.hetero.models.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttributeDao extends JpaRepository<ProductAttribute, Integer> {
}
