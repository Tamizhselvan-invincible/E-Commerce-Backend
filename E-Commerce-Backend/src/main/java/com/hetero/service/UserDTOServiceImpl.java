package com.hetero.service;

import com.hetero.models.UserDTO;
import com.hetero.repository.UserDTODao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDTOServiceImpl implements UserDTOService {

    @Autowired
    private UserDTODao userDetailsDao;

    private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder(12);

    @Override
    public UserDTO registerUser (UserDTO userDTO) {
        userDTO.setPassword(bCryptEncoder.encode(userDTO.getPassword()));
        return userDetailsDao.save(userDTO);
    }
}
