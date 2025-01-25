package com.hetero.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("Id")
	private int id;

	@Column(nullable = false)
	@JsonProperty("Name")
	private String name;

	@Column(nullable = false)
	@JsonProperty("PhoneNumber")
	private String phoneNumber;

	@Column(nullable = false)
	@Pattern(regexp = "[A-Za-z0-9\\s-]{3,}", message = "Not a valid street no")
	@JsonProperty("Street")
	private String street;

	@Column(nullable = false)
	@NotNull(message = "City name cannot be null")
	@Pattern(regexp = "[A-Za-z\\s]{2,}", message = "Not a valid city name")
	@JsonProperty("City")
	private String city;

	@Column(nullable = false)
	@NotNull(message = "State name cannot be null")
	@JsonProperty("State")
	private String state;

	@Column(nullable = false)
	@NotNull(message = "Pincode cannot be null")
	@Pattern(regexp = "[0-9]{6}", message = "Pin code not valid. Must be 6 digits")
	@JsonProperty("PostalCode")
	private String postalCode;

	@Column(nullable = false)
	@JsonProperty("Country")
	private String country;

	@Temporal(TemporalType.TIMESTAMP)
	@PastOrPresent
	@Column(nullable = false)
	@JsonProperty("DateTime")
	private Date dateTime;

   @JsonProperty("SelectedAddress")
	private boolean selectedAddress;

	// Getters and setters omitted for brevity
}

/*
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	
	@Pattern(regexp = "[A-Za-z0-9\\s-]{3,}", message = "Not a valid street no")
	private String streetNo;
	
	@Pattern(regexp = "[A-Za-z0-9\\s-]{3,}", message = "Not a valid building name")
	private String buildingName;
	
	@NotNull
	@Pattern(regexp = "[A-Za-z0-9\\s-]{3,}", message = "Not a valid locality name")
	private String locality;
	
	@NotNull(message = "City name cannot be null")
	@Pattern(regexp = "[A-Za-z\\s]{2,}", message = "Not a valid city name")
	private String city;
	
	@NotNull(message = "State name cannot be null")
	private String state;
	
	@NotNull(message = "Pincode cannot be null")
	@Pattern(regexp = "[0-9]{6}", message = "Pincode not valid. Must be 6 digits")
	private String pincode;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Customer customer;
}
*/