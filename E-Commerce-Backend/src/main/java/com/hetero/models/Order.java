package com.hetero.models;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@JsonProperty("userId")
	private String userId;

	@Enumerated(EnumType.STRING)
	@JsonProperty("status")
	private OrderStatus status;

	@Column(nullable = false)
	@JsonProperty("totalAmount")
	private double totalAmount;

	@JsonProperty("shippingCost")
	private double shippingCost;

	@JsonProperty("taxCost")
	private double taxCost;



	@JsonProperty("orderDate")
	@Column(name = "order_date",nullable = false) // Matches the database column name
	private LocalDate date;


	@JsonProperty("paymentMethod")
	private String paymentMethod;


	@ManyToOne
	@JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
	@JsonProperty("shippingAddress")
	private Address shippingAddress;

	@ManyToOne
	@JoinColumn(name = "billing_address_id", referencedColumnName = "id")
	@JsonProperty("billingAddress")
	private Address billingAddress;


	@JsonProperty("billingAddressSameAsShipping")
	private boolean billingAddressSameAsShipping;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(
			name = "order_items",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "cart_item_id")
	)
	@JsonProperty("items")
	private List<CartItem> items;

	@JsonProperty("deliveryDate")
	private LocalDate deliveryDate;


	// Getters and setters omitted for brevity
}
