package com.hetero.controller;


import com.hetero.models.AppRole;
import com.hetero.models.User;
import com.hetero.models.UserDTO;
import com.hetero.repository.UserDao;
import com.hetero.security.TokenBlockListService;
import com.hetero.service.LoginRegisterService;
import com.hetero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class LoginRegisterController {

    @Autowired
    private LoginRegisterService loginRegisterService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @Autowired
    TokenBlockListService tokenBlockListService;


//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(HttpServletRequest request,  HttpServletResponse response) {
//        // Extract token from Authorization header
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            tokenBlockListService.blacklistToken(token);
//        }
//
//        // Clear JWT token cookie for browser
//        Cookie jwtCookie = new Cookie("JWT_TOKEN", null);
//        jwtCookie.setPath("/");
//        jwtCookie.setHttpOnly(true);
//        jwtCookie.setMaxAge(0);
//        response.addCookie(jwtCookie);
//
//        // Clear authentication context
//        SecurityContextHolder.clearContext();
//
//        // Invalidate session
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        return ResponseEntity.ok("Logged out successfully");
//    }
//
//
//    @GetMapping("/admin/logout")
//    public ResponseEntity<?> logoutForBrowsers(HttpServletRequest request, HttpServletResponse response) {
//        // Clear JWT token cookie
//        Cookie jwtCookie = new Cookie("JWT_TOKEN", null);
//        jwtCookie.setPath("/");
//        jwtCookie.setHttpOnly(true);
//        jwtCookie.setMaxAge(0);
//        response.addCookie(jwtCookie);
//
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            tokenBlockListService.blacklistToken(token);
//        }
//        // Clear authentication context
//        SecurityContextHolder.clearContext();
//
//        // Optional: Invalidate session
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//
//        return ResponseEntity.ok("Logged out successfully");
//    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) {

        User user = new User();
        user.setRole(AppRole.admin);
        user.setUserName(userDTO.getUsername());

        userDao.save(user);
        return loginRegisterService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        System.out.println("DEBUG::: Logged in USER  "+userDTO);
        return loginRegisterService.verifyUser(userDTO);
    }
}
