package com.hetero.service;

import com.hetero.models.Order;
import com.hetero.models.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User save(User user);
    List<User> findAll();
    User findById(Integer id);
    List<Order> findUserOrders(Integer id);
    User update(User user);
    User updateSpecificFields(Map<String, Object> updates);
    String delete(Integer id);

    User findByUserName(String name);

    User findAdminDetails();
}
