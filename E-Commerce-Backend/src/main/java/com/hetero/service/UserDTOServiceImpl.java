package com.hetero.service;

import com.hetero.models.UserDTO;
import com.hetero.repository.UserDTODao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDTOServiceImpl implements UserDTOService {

    @Autowired
    private UserDTODao userDetailsDao;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authManager;

    private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder(12);

    @Override
    public UserDTO registerUser (UserDTO userDTO) {
        userDTO.setPassword(bCryptEncoder.encode(userDTO.getPassword()));
        return userDetailsDao.save(userDTO);
    }

    @Override
    public String verifyUser (UserDTO userDTO) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(),
                        userDTO.getPassword())
        );

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(userDTO.getUsername());
        }
        return "Fail";
    }

}
