package com.hetero.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "product_variations")
public class ProductVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Id")
    private int id;


    @Column(name = "image_url")
    @JsonProperty("Image")
    private String image;

    @Column(name = "description", length = 500)
    @JsonProperty("Description")
    private String description;

    @Column(name = "price", nullable = false)
    @JsonProperty("Price")
    private double price;

    @Column(name = "sale_price")
    @JsonProperty("SalePrice")
    private double salePrice;

    @Column(name = "sku", nullable = false, length = 50)
    @JsonProperty("SKU")
    private String sku;

    @Column(name = "stock", nullable = false)
    @JsonProperty("Stock")
    private int stock;

    @Column(name = "sold_quantity")
    @JsonProperty("SolidQuantity")
    private int soldQuantity;


    @ElementCollection
    @CollectionTable(
            name = "variation_attributes",
            joinColumns = @JoinColumn(name = "variation_id")
    )
    @MapKeyColumn(name = "attribute_id")
    @Column(name = "attribute_value")
    @JsonProperty("AttributeValues")
    private Map<String, String> attributeValues = new HashMap<>();



}
