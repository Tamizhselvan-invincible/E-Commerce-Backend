package com.hetero.service;

import com.hetero.models.User;

public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByUsernameAndPassword(String username, String password);
    User findByEmailAndPassword(String email, String password);
    void save(User user);

}
