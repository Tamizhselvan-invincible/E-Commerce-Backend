package com.hetero.service;


import com.hetero.models.UserDTO;

public interface LoginRegisterService {


    public UserDTO registerUser(UserDTO userDTO);

    String verifyUser (UserDTO userDTO);
}
