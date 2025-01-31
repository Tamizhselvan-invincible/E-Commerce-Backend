package com.hetero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hetero.exception.ProductNotFoundException;
import com.hetero.models.CartDTO;
import com.hetero.models.CartItem;
import com.hetero.models.Product;
import com.hetero.models.ProductStatus;
import com.hetero.repository.ProductDao;

import javax.transaction.Transactional;

@Service
public class CartItemServiceImpl implements CartItemService{

	@Autowired
	ProductDao productDao;



//	@Override
//	public CartItem addItemToCart(CartDTO cartdto) {
//
//		// TODO Auto-generated method stub
//
////		Product existingProduct = productDao.findById(cartdto.getProductId()).orElseThrow( () -> new ProductException("Product Not found"));
//
//		Optional<Product> opt = productDao.findById(cartdto.getProductId());
//
//		if(opt.isEmpty())
//			throw new ProductNotFoundException("Product not found");
//
//		Product existingProduct = opt.get();
//
//		CartItem newItem = new CartItem();
//
//		newItem.setCartProduct(existingProduct);
//
//		newItem.setCartItemQuantity(1);
//
//		return newItem;
//	}

}
