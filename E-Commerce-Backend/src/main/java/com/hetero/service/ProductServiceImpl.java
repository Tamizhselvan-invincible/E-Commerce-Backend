package com.hetero.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.hetero.exception.ResourceNotFoundException;
import com.hetero.models.*;
import com.hetero.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Autowired
	private ProductAttributeDao productAttributeDaoDao;

	@Autowired
	private ProductVariationDao productVariationDaoDao;

	@Override
	public Product addProductToCatalog(Product product) {
       if(product.getProductAttributes() != null) {
           productAttributeDaoDao.saveAll(product.getProductAttributes());
	   }
	   if(product.getProductVariations() != null) {
		   productVariationDaoDao.saveAll(product.getProductVariations());
	   }
        return productDao.save(product);
	}

	@Override
	public Product getProductFromCatalogById (Integer id) {
		Optional<Product> product = productDao.findById(id);
		if(product.isPresent()) {
			return product.get();
		} else {
			return null;
		}
	}

	@Override
	public String deleteProductFromCatalog (Integer id) {
		Optional<Product> product = productDao.findById(id);
		if(product.isPresent()) {
			productDao.deleteById(id);
			return "Product deleted successfully";

		}else {
			return "Product not found";
		}
	}

	@Override
	public Product updateProductInCatalog (Integer productID,Product product) {
		Product oldProduct = getProductFromCatalogById(productID);
         if(oldProduct == null) {
	           return null;
            }

		 oldProduct.setTitle(product.getTitle());
		 oldProduct.setThumbnail(product.getThumbnail());

		 oldProduct.setCategoryId(product.getCategoryId());

		 oldProduct.setSoldQuantity(product.getSoldQuantity());
		 oldProduct.setStock(product.getStock());

		 oldProduct.setSku(product.getSku());
		 oldProduct.setPrice(product.getPrice());
		 oldProduct.setSalePrice(product.getSalePrice());
		 oldProduct.setFeatured(product.isFeatured());


		 oldProduct.setProductType(product.getProductType());
		 oldProduct.setDate(product.getDate());
		 oldProduct.setDescription(product.getDescription());
		 oldProduct.setBrand(product.getBrand());
		 oldProduct.setImages(product.getImages());


		if(product.getProductAttributes() != null) {
			productAttributeDaoDao.saveAll(product.getProductAttributes());
		}

		if(product.getProductVariations() != null) {
			productVariationDaoDao.saveAll(product.getProductVariations());
		}
		return productDao.save(oldProduct);
	}

	@Override
	public Product updateProductSpecificFields(Integer productId, Map<String, Object> updates) {
		// Fetch the product by ID
		Product product = productDao.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

		// Update specific fields
		updates.forEach((field, value) -> {
			switch (field) {
				case "Title":
					product.setTitle((String) value);
					break;

				case "Stock":
					product.setStock((Integer) value);
					break;

				case "SKU":
					product.setSku((String) value);
					break;

				case "Price":
					product.setPrice((Double) value);
					break;

				case "Thumbnail":
					product.setThumbnail((String) value);
					break;

				case "ProductType":
					product.setProductType((String) value);
					break;

				case "Date":
					product.setDate(new Date((Long) value)); // Assuming date is passed as a timestamp
					break;

				case "SalePrice":
					product.setSalePrice((Double) value);
					break;

				case "IsFeatured":
					product.setFeatured((Boolean) value);
					break;

				case "Brand":
					// Handle "Brand" field, converting the map to a Brand entity
					Map<String, Object> brandData = (Map<String, Object>) value;
					Brand brand = new Brand();
					brand.setId((Integer) brandData.get("id"));
					brand.setBrandName((String) brandData.get("name"));
					product.setBrand(brand);
					break;

				case "CategoryId":
					product.setCategoryId((String) value);
					break;

				case "Description":
					product.setDescription((String) value);
					break;

				case "Images":
					product.setImages((List<String>) value); // Assuming a list of image URLs is passed
					break;

				case "SoldQuantity":
					product.setSoldQuantity((Integer) value);
					break;

				default:
					throw new IllegalArgumentException("Unsupported field: " + field);
			}
		});


		// Save the updated product
		return productDao.save(product);
	}

	@Override
	public List<Product> getAllProductsInCatalog () {
		return productDao.findAll();
	}

	@Override
	public List<ProductDTO> getProductsOfStatus (ProductStatus status) {
		return List.of();
	}

	@Override
	public Product updateProductSoldQuantityWithId (Integer id, ProductDTO prodDTO) {
		return null;
	}

	@Override
	public ProductCategory addProductCategory (ProductCategory productCategory) {
		return productCategoryDao.save(productCategory);
	}

	@Override
	public ProductCategory getProductCategoryById (Integer id) {
		return productCategoryDao.findById(id).get();
	}

	@Override
	public List<ProductCategory> getAllProductCategories () {
		return productCategoryDao.findAll();
	}

	@Override
	public List<ProductCategory> getAllProductCategoriesByProductId (Integer productId) {
		return productCategoryDao.findAllByProductId(productId);
	}

	@Override
	public ProductCategory updateProductCategory (Integer id,ProductCategory productCategory) {
		ProductCategory oldCategory = productCategoryDao.findById(id).get();

		oldCategory.setCategoryId(productCategory.getCategoryId());
		oldCategory.setProductId(productCategory.getProductId());
		return productCategoryDao.save(oldCategory);
	}

	@Override
	public String deleteProductCategory (Integer id) {
		Optional<ProductCategory> productCategory = productCategoryDao.findById(id);
		if(productCategory.isPresent()) {
			productCategoryDao.deleteById(id);
			return "Product deleted successfully";
		}else {
			return "Product not found";
		}
	}


}
