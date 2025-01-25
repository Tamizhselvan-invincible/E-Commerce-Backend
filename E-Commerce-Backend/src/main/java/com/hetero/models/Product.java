package com.hetero.models;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "products")
public class Product{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("Id")
	private Integer id;

	@Column(nullable = false)
	@JsonProperty("Title")
	private String title;

	@Column(nullable = false)
	@JsonProperty("Stock")
	private int stock;

	@Column
	@JsonProperty("SKU")
	private String sku;

	@Column(nullable = false)
	@JsonProperty("Price")
	private double price;

	@Column(nullable = false)
	@JsonProperty("Thumbnail")
	private String thumbnail;

	@Column(nullable = false)
	@JsonProperty("ProductType")
	private String productType;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("Date")
	private Date date;

	@Column(name = "sale_price", nullable = false)
	@JsonProperty("SalePrice")
	private double salePrice;

	@Column(name = "is_featured")
	@JsonProperty("IsFeatured")
	private boolean isFeatured;

	@ManyToOne
	@JoinColumn(name = "brand_id", referencedColumnName = "id")
	@JsonProperty("Brand")
	private Brand brand;

	@Column(name = "category_id")
	@JsonProperty("CategoryId")
	private String categoryId;

	@Column(length = 500)
	@JsonProperty("Description")
	private String description;

	@ElementCollection
	@CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
	@Column(name = "image_url")
	@JsonProperty("Images")
	private List<String> images;

	@Column(name = "sold_quantity")
	@JsonProperty("SoldQuantity")
	private int soldQuantity;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "product_id")
	@JsonProperty("ProductAttributes")
	private List<ProductAttribute> productAttributes  = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonProperty("ProductVariations")
	@JoinColumn(name = "product_id")
	private List<ProductVariation> productVariations = new ArrayList<>();


}

