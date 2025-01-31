package com.hetero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hetero.models.CartItem;
import org.springframework.stereotype.Repository;


@Repository
public interface CartItemDao extends JpaRepository<CartItem, Integer>{

}
