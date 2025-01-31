package com.hetero.service;


import com.hetero.models.Order;
import com.hetero.models.User;
import com.hetero.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Transactional
    @Override
    public User save (User user) {
      return userDao.save(user);
    }

    @Override
    public List<User> findAll () {
        return userDao.findAll();
    }

    @Override
    public User findById (Integer id) {
        return userDao.findById(id).get();
    }

    @Override
    public List<Order> findUserOrders (Integer id) {
        return userDao.findById(id).get().getOrders();
    }


    @Transactional
    @Override
    public User update (User user) {

        User oldUser = userDao.findById(user.getId()).get();
        if(oldUser == null) {
            throw new EntityNotFoundException("User not found");
        }

        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setUserName(user.getUserName());
        oldUser.setMobileNo(user.getMobileNo());
        oldUser.setEmailId(user.getEmailId());
        oldUser.setDateCreated(user.getDateCreated());
        oldUser.setDateUpdated(new Date());
        oldUser.setProfilePicture(user.getProfilePicture());
        oldUser.setRole(user.getRole());
        oldUser.setOrders(user.getOrders());
        oldUser.setAddresses(user.getAddresses());

        return userDao.save(user);
    }


    @Transactional
    @Override
    public User updateSpecificFields (Map<String, Object> updates) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userDao.findByUserName(username);

        if(user == null) {
            throw new EntityNotFoundException("User not found");
        }
        updates.forEach((field, value) -> {
            switch (field){
                case "FirstName":
                    user.setFirstName((String) value);
                    break;
                case "LastName":
                    user.setLastName((String) value);
                    break;
                case "UserName":
                    user.setUserName((String) value);
                    break;
                case "MobileNo":
                    user.setMobileNo((String) value);
                    break;
                case "EmailId":
                    user.setEmailId((String) value);
                    break;
                case "ProfilePicture":
                    user.setProfilePicture((String) value);
                    break;
               case "Role":
                   user.setProfilePicture((String) value);
                   break;

            }
        });
    return userDao.save(user);
    }

    @Transactional
    @Override
    public String  delete (Integer id) {
       User user = userDao.findById(id).get();
       if(user == null) {
           throw new EntityNotFoundException("User not found");
       }else {
           userDao.delete(user);
           return "User deleted Successfully";
       }
    }

    @Override
    public User findByUserName (String name) {
        return userDao.findByUserName(name);
    }

    @Override
    public User findAdminDetails () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userDao.findByUserName(username);
    }
}
