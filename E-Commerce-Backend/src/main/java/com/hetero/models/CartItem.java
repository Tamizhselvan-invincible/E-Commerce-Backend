package com.hetero.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "cart_items")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("productId")
	private Integer cartItemId;


	@JsonProperty("title")
	private String title;

	@URL
	@JsonProperty("image")
	private String imageUrl;


	@JsonProperty("validationId")
	private String variationId;

	@JsonProperty("brandName")
	private String brandName;

	@JsonProperty("quantity")
	private Integer cartItemQuantity;

	@ManyToOne
	@JoinColumn(name = "cart_id", nullable = false) // Foreign key column in CartItem table
	private Cart cart;


	@ElementCollection
	@CollectionTable(
			name = "selected_variations",
			joinColumns = @JoinColumn(name = "cart_item_id")
	)
	@Column(name = "variation_value")
	@MapKeyColumn(name = "variation_key")
	@JsonProperty("SelectedVariation")
	private Map<String, String> selectedVariation = new HashMap<>();


}
