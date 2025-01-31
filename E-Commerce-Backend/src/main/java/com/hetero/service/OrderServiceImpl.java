package com.hetero.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hetero.models.Address;
import com.hetero.models.CartItem;
import com.hetero.models.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hetero.exception.OrderException;
import com.hetero.models.Order;
import com.hetero.repository.OrderDao;

import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

	@Override
	public Order saveOrder (Order order) {
		return orderDao.save(order);
	}

	@Override
	public Order getOrderByOrderId (Integer OrderId) throws OrderException {
		return orderDao.findById(OrderId).orElseThrow(() -> new OrderException("No order exists with given OrderId " + OrderId));

	}

	@Override
	public List<Order> getAllOrders () throws OrderException {
		// TODO Auto-generated method stub
		List<Order> orders = orderDao.findAll();
		if (!orders.isEmpty())
			return orders;
		else
			throw new OrderException("No Orders exists on your account");
	}

	@Override
	public List<Order> getAllOrdersByDate (LocalDate orderDate) throws OrderException {
		return orderDao.findByDate(orderDate);
	}

	@Transactional
	@Override
	public Order updateOrderByOrder (int id, Order order) {
		Optional<Order> existingOrder = orderDao.findById(id);
		if (existingOrder.isPresent()) {
			Order updatedOrder = existingOrder.get();
			updatedOrder.setStatus(order.getStatus());
			updatedOrder.setTotalAmount(order.getTotalAmount());
			updatedOrder.setShippingCost(order.getShippingCost());
			updatedOrder.setTaxCost(order.getTaxCost());
			updatedOrder.setPaymentMethod(order.getPaymentMethod());
			updatedOrder.setBillingAddress(order.getBillingAddress());
			updatedOrder.setShippingAddress(order.getShippingAddress());
			updatedOrder.setItems(order.getItems());
			updatedOrder.setDeliveryDate(order.getDeliveryDate());
			return orderDao.save(updatedOrder);
		}
		throw new RuntimeException("Order not found");
	}

	@Transactional
	@Override
	public Order updateSpecificOrderValues (int id, Map<String, Object> updates) throws OrderException {

		Optional<Order> existingOrder = Optional.ofNullable(orderDao.findById(id)
				.orElseThrow(() -> new OrderException("")));

		if (existingOrder.isPresent()) {
			Order newOrder = existingOrder.get();


		updates.forEach((key, value) -> {
			switch (key) {
				case "userId":
					newOrder.setUserId( (String) value);
					break;
				case "status":
					newOrder.setStatus((OrderStatus) value);
				    break;
				case "totalAmount":
					newOrder.setTotalAmount((Double) value);
					break;
				case "shippingCost":
					newOrder.setShippingCost((Double) value);
					break;
				case "taxCost":
					newOrder.setTaxCost((Double) value);
					break;
				case "paymentMethod":
					newOrder.setPaymentMethod((String) value);
					break;
				case "billingAddress":
					newOrder.setBillingAddress((Address) value);
					break;
				case "shippingAddress":
					newOrder.setShippingAddress((Address) value);
					break;
				case "items":
					newOrder.setItems((List<CartItem>) value);
				   break;
				case "deliveryDate":
					newOrder.setDeliveryDate((LocalDate) value);
					break;
				case "orderDate":
					newOrder.setDate((LocalDate) value);
					break;
				case "billingAddressSameAsShipping":
					newOrder.setBillingAddressSameAsShipping((Boolean) value);
					break;
				default:
					throw new IllegalArgumentException("Unsupported field: " + value);
			}
		});

			return orderDao.save(newOrder);
	} else {
		return null;
	}

}

@Transactional
	@Override
	public String cancelOrderByOrderId(Integer id) throws OrderException {
      if (orderDao.findById(id).isPresent()) {
		  orderDao.deleteById(id);
		  return "Order cancelled";
	  }else {
		  throw new OrderException("Order not found");
	  }
	}


}
