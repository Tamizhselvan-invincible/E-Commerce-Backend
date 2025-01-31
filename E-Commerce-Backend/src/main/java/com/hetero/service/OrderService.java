package com.hetero.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.hetero.exception.LoginException;
import com.hetero.exception.OrderException;
import com.hetero.models.Order;
import com.hetero.models.OrderDTO;


public interface OrderService {

	Order saveOrder(Order order);
	public Order getOrderByOrderId(Integer OrderId) throws OrderException;

	public List<Order> getAllOrders() throws OrderException;

	public List<Order> getAllOrdersByDate(LocalDate orderDate) throws OrderException;

	public  Order updateOrderByOrder(int id, Order order);

	public Order updateSpecificOrderValues(int id, Map<String,Object> values) throws OrderException;

	public String cancelOrderByOrderId(Integer id) throws OrderException ;


	/*
	public Order updateOrderByOrder(OrderDTO order,Integer OrderId,String token) throws OrderException,LoginException;


	public Order cancelOrderByOrderId(Integer OrderId,String token) throws OrderException;

	public Customer getCustomerByOrderid(Integer orderId) throws OrderException;

	public Customer getCustomerIdByToken(String token) throws CustomerNotFoundException;

    	public Order saveOrder(OrderDTO odto,String token) throws LoginException, OrderException;

	 */
}
