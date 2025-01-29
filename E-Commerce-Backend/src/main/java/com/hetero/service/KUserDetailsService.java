package com.hetero.service;

import com.hetero.models.UserDTO;
import com.hetero.repository.UserDTODao;
import com.hetero.models.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class KUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDTODao userDetailsDao;

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {

        UserDTO user = userDetailsDao.findByUsername(username);

        if (user == null) {
            System.out.println("DEBUG:::: User Not Found");
            throw new UsernameNotFoundException(username);
        }

        return new UserPrinciple(user);
    }
}
