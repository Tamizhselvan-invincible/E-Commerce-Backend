package com.hetero.service;

import java.util.List;
import java.util.Map;

import com.hetero.models.*;

public interface ProductService {

	public Product addProductToCatalog(Product product);

	public Product getProductFromCatalogById(Integer id);

	public String deleteProductFromCatalog(Integer id);

	public Product updateProductInCatalog (Integer productId,Product product);

	public Product updateProductSpecificFields(Integer productId, Map<String, Object> updates);

	public List<Product> getAllProductsInCatalog ();

	public List<ProductDTO> getProductsOfStatus(ProductStatus status);

	public Product updateProductSoldQuantityWithId (Integer id, ProductDTO prodDTO);

	public ProductCategory addProductCategory(ProductCategory productCategory);

	public ProductCategory getProductCategoryById(Integer id);

	public List<ProductCategory> getAllProductCategories();

	public List<ProductCategory> getAllProductCategoriesByProductId(Integer id);


	public ProductCategory updateProductCategory(Integer id,ProductCategory productCategory);

	public String deleteProductCategory(Integer id);

}
