package com.hetero.controller;


import com.hetero.models.UserDTO;
import com.hetero.service.UserDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDTOController {

    @Autowired
    private UserDTOService userDTOService;


    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return userDTOService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        System.out.println("DEBUG::: Login USER  "+userDTO);
        return userDTOService.verifyUser(userDTO);
    }
}
