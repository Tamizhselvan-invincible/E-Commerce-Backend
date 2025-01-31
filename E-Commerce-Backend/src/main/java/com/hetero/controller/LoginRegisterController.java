package com.hetero.controller;


import com.hetero.models.UserDTO;
import com.hetero.service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class LoginRegisterController {

    @Autowired
    private LoginRegisterService loginRegisterService;


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Clear authentication context
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return loginRegisterService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        System.out.println("DEBUG::: Login USER  "+userDTO);
        return loginRegisterService.verifyUser(userDTO);
    }
}
