package com.hetero.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.hetero.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hetero.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService pService;

	// this method adds new product to catalog by seller(if seller is new it adds
	// seller as well
	// if seller is already existing products will be mapped to same seller) and
	// returns added product

	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {

		Product prod = pService.addProductToCatalog(product);

		return new ResponseEntity<>(prod, HttpStatus.ACCEPTED);

	}

	// This method gets the product which needs to be added to the cart returns
	// product

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer id) {

		Product prod = pService.getProductFromCatalogById(id);


		return new ResponseEntity<>(prod, HttpStatus.FOUND);

	}

	// This method will delete the product from catalog and returns the response
	// This will be called only when the product qty will be zero or seller wants to
	// delete for any other reason

	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {

		String res = pService.deleteProductFromCatalog(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateProduct(
			@PathVariable Integer productId,
			@RequestBody Product product) {

		Product updatedProduct = pService.updateProductInCatalog(productId,product);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	@PutMapping("/products/{productId}/fields")
	public ResponseEntity<Product> updateProductSpecificFields(
			@PathVariable Integer productId,
			@RequestBody Map<String, Object> updates) {

		Product updatedProduct = pService.updateProductSpecificFields(productId, updates);

		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}


	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {

		List<Product> list = pService.getAllProductsInCatalog();

		return new ResponseEntity<>(list, HttpStatus.OK);
	}



	///Product Category Controllers

	@PostMapping("/productCategories")
	public ResponseEntity<ProductCategory> addProductCategory(@Valid @RequestBody ProductCategory productCategory) {

		ProductCategory prod = pService.addProductCategory(productCategory);

		return new ResponseEntity<>(prod, HttpStatus.ACCEPTED);

	}

	@GetMapping("/productCategories/{id}")
	public ResponseEntity<ProductCategory> getProductCategory(@PathVariable Integer id) {

		ProductCategory prod = pService.getProductCategoryById(id);

		return new ResponseEntity<ProductCategory>(prod, HttpStatus.FOUND);

	}


	@PutMapping("/productCategories/{productId}")
	public ResponseEntity<ProductCategory> updateProductCategory(
			@PathVariable Integer productId,
			@RequestBody ProductCategory productCategory) {

		ProductCategory updatedProduct = pService.updateProductCategory(productId,productCategory);

		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	@DeleteMapping("/productCategories/{id}")
	public ResponseEntity<String> deleteProductCategory(@PathVariable Integer id) {
		String res = pService.deleteProductCategory(id);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

	@GetMapping("/productCategories")
	public ResponseEntity<List<ProductCategory>> getAllProductCategories() {
		List<ProductCategory> list = pService.getAllProductCategories();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}



	///NOT NEEDED AnyMore Just For Future Modifications.
	/// Because Current Model and Client Doesn't Need this Services

	@GetMapping("/products/status/{status}")
	public ResponseEntity<List<ProductDTO>> getProductsWithStatusHandler(@PathVariable String status) {

		ProductStatus ps = ProductStatus.valueOf(status.toUpperCase());
		List<ProductDTO> list = pService.getProductsOfStatus(ps);

		return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);

	}


	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateQuantityOfProduct(@PathVariable Integer id,@RequestBody ProductDTO prodDto){

		 Product prod =   pService.updateProductSoldQuantityWithId(id, prodDto);

		 return new ResponseEntity<Product>(prod,HttpStatus.ACCEPTED);
	}

}
