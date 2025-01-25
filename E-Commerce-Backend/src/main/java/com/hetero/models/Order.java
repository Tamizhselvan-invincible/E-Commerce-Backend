//package com.hetero.models;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.PastOrPresent;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//
//
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Entity
//@Table(name="orders")
//public class Order {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@Column(nullable = false)
//	private String userId;
//
//	@Enumerated(EnumType.STRING)
//	private OrderStatus status;
//
//	@Column(nullable = false)
//	private double totalAmount;
//
//	private double shippingCost;
//
//	private double taxCost;
//
//	@Column(nullable = false)
//	private LocalDateTime orderDate;
//
//	private String paymentMethod;
//
//	@ManyToOne
//	@JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
//	private Address shippingAddress;
//
//	@ManyToOne
//	@JoinColumn(name = "billing_address_id", referencedColumnName = "id")
//	private Address billingAddress;
//
//
//	private boolean billingAddressSameAsShipping;
//
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinTable(
//			name = "order_items",
//			joinColumns = @JoinColumn(name = "order_id"),
//			inverseJoinColumns = @JoinColumn(name = "cart_item_id")
//	)
//	private List<CartItem> items;
//
//	private LocalDateTime deliveryDate;
//
//	// Getters and setters omitted for brevity
//}
