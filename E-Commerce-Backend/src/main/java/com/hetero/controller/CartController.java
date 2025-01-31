package com.hetero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.hetero.models.Cart;
import com.hetero.models.CartDTO;
import com.hetero.repository.CartDao;
import com.hetero.service.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private CartDao cartDao;


}
