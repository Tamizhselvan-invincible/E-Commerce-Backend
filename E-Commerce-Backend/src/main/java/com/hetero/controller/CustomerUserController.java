package com.hetero.controller;

import com.hetero.models.Order;
import com.hetero.models.User;
import com.hetero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerUserController {

    @Autowired
    UserService userService;


    @PostMapping("/users")
    public ResponseEntity<User> addUser (@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/users")
    public List<User> getAllUsers () {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserDetails (@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/users/admin")
    public ResponseEntity<User> getAdminUsers () {
        return ResponseEntity.ok(userService.findAdminDetails());
    }


    @GetMapping("/users/orders/{id}")
    public ResponseEntity<List<Order>> getOrderDetails (@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user.getOrders());
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser (@PathVariable Integer userId, @RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }



    @PutMapping("/users/updates/{id}")
    public ResponseEntity<User> updateSpecificUserDetails (@PathVariable Integer id, @RequestBody Map<String, Object> details) {
        return ResponseEntity.ok(userService.updateSpecificFields(details));
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser (@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
