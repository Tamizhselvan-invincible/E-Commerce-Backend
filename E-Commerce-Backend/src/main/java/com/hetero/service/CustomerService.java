package com.hetero.service;

import java.util.List;

import com.hetero.exception.CustomerException;
import com.hetero.exception.CustomerNotFoundException;
import com.hetero.models.Address;
import com.hetero.models.CreditCard;
import com.hetero.models.Customer;
import com.hetero.models.CustomerDTO;
import com.hetero.models.CustomerUpdateDTO;
import com.hetero.models.Order;
import com.hetero.models.SessionDTO;

public interface CustomerService {
	
	public Customer addCustomer(Customer customer) throws CustomerException;
	
	public Customer getLoggedInCustomerDetails(String token) throws CustomerNotFoundException;
	
	public List<Customer> getAllCustomers(String token) throws CustomerNotFoundException;
	
	public Customer updateCustomer(CustomerUpdateDTO customer, String token) throws CustomerNotFoundException;
	
	public Customer updateCustomerMobileNoOrEmailId(CustomerUpdateDTO customerUpdateDTO, String token) throws CustomerNotFoundException;
	
	public Customer updateCreditCardDetails(String token, CreditCard card) throws CustomerException;
	
	public SessionDTO updateCustomerPassword(CustomerDTO customerDTO, String token) throws CustomerNotFoundException;
	
	public SessionDTO deleteCustomer(CustomerDTO customerDTO, String token) throws CustomerNotFoundException;
	
	public Customer updateAddress(Address address, String type, String token) throws CustomerException;
	
	public Customer deleteAddress(String type, String token) throws CustomerException, CustomerNotFoundException;

	public List<Order> getCustomerOrders(String token) throws CustomerException; 

}
