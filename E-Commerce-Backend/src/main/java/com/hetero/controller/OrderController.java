package com.hetero.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hetero.models.Order;
import com.hetero.service.OrderService;

@RestController
public class OrderController {


	@Autowired
	private OrderService orderService;

	@PostMapping("/order/place")
	public ResponseEntity<Order> addTheNewOrder(@Valid @RequestBody Order order){

		Order savedorder = orderService.saveOrder(order);
		return new ResponseEntity<>(savedorder,HttpStatus.CREATED);

	}

	@GetMapping("/orders")
	public List<Order> getAllOrders(){
		List<Order> listOfAllOrders = orderService.getAllOrders();
		return listOfAllOrders;

	}

	@GetMapping("/orders/{orderId}")
	public Order getOrdersByOrderId(@PathVariable Integer orderId) {
		return orderService.getOrderByOrderId(orderId);
	}


	///Delete Order
	@DeleteMapping("/orders/{orderId}")
	public String cancelTheOrderByOrderId(@PathVariable Integer orderId){

		return orderService.cancelOrderByOrderId(orderId);
	}


	@PutMapping("/orders/{orderId}")
	public ResponseEntity<Order> updateOrderByOrderId(@PathVariable Integer orderId,@Valid @RequestBody Order order){

		Order updatedOrder= orderService.updateOrderByOrder(orderId,order);
		return new ResponseEntity<Order>(updatedOrder,HttpStatus.ACCEPTED);

	}

	///Update Specific Order Values
    public ResponseEntity<Order> updateSpecificOrderValues(@PathVariable Integer orderId, @Valid @RequestBody Map<String,Object> values){

		Order updatedOrder= orderService.updateSpecificOrderValues(orderId,values);
		return new ResponseEntity<>(updatedOrder,HttpStatus.ACCEPTED);
	}

	@GetMapping("/orders/by/date")
	public List<Order> getAllOrdersByDate(@RequestParam String date){

		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate ld=LocalDate.parse(date,dtf);
		return orderService.getAllOrdersByDate(ld);
	}



}
