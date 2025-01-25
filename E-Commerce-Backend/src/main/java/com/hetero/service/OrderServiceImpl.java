//package com.hetero.service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.hetero.exception.LoginException;
//import com.hetero.exception.OrderException;
//import com.hetero.models.CartItem;
//import com.hetero.models.Customer;
//import com.hetero.models.Order;
//import com.hetero.models.OrderDTO;
//import com.hetero.models.OrderStatusValues;
//import com.hetero.repository.OrderDao;
//
//@Service
//public class OrderServiceImpl implements OrderService {
//	@Autowired
//	private OrderDao orderDao;
//
//	@Override
//	public Order saveOrder (Order order) {
//		return orderDao.save(order);
//	}
//
//	@Override
//	public Order getOrderByOrderId(Integer OrderId) throws OrderException {
//		return orderDao.findById(OrderId).orElseThrow(()-> new OrderException("No order exists with given OrderId "+ OrderId));
//
//	}
//
//	@Override
//	public List<Order> getAllOrders() throws OrderException {
//		// TODO Auto-generated method stub
//		List<Order> orders = orderDao.findAll();
//		if(!orders.isEmpty())
//			return orders;
//		else
//			throw new OrderException("No Orders exists on your account");
//	}
//
//	@Override
//	public List<Order> getAllOrdersByDate(LocalDate date) throws OrderException {
//        return orderDao.findByDate(date);
//	}
//
//	@Override
//	public Order updateOrderByOrder(int id, Order order) {
//		Optional<Order> existingOrder = orderDao.findById(id);
//		if (existingOrder.isPresent()) {
//			Order updatedOrder = existingOrder.get();
//			updatedOrder.setStatus(order.getStatus());
//			updatedOrder.setTotalAmount(order.getTotalAmount());
//			updatedOrder.setShippingCost(order.getShippingCost());
//			updatedOrder.setTaxCost(order.getTaxCost());
//			updatedOrder.setPaymentMethod(order.getPaymentMethod());
//			updatedOrder.setBillingAddress(order.getBillingAddress());
//			updatedOrder.setShippingAddress(order.getShippingAddress());
//			updatedOrder.setItems(order.getItems());
//			updatedOrder.setDeliveryDate(order.getDeliveryDate());
//			return orderDao.save(updatedOrder);
//		}
//		throw new RuntimeException("Order not found");
//	}
//
//	@Override
//	public String cancelOrderByOrderId(int id) throws OrderException {
//      if (orderDao.findById(id).isPresent()) {
//		  orderDao.deleteById(id);
//		  return "Order cancelled";
//	  }else {
//		  throw new OrderException("Order not found");
//	  }
//	}
//
//
//}
