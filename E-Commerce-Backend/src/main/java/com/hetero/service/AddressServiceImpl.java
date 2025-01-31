package com.hetero.service;

import com.hetero.models.Address;
import com.hetero.models.User;
import com.hetero.repository.AddressDao;
import com.hetero.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressDao addressDao;

    @Autowired
    UserService userService;

    @Override
    public List<Address> fetchUserAddresses (Integer userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return List.of();
        }
        return user.getAddresses();
    }

    @Transactional
    @Override
    public void updateSelectedField (Integer addressId, boolean selected) {

        Address address = addressDao.findById(addressId).get();
        address.setSelectedAddress(selected);
    }

    @Transactional
    @Override
    public Address addAddress (Address address) {
        return addressDao.save(address);
    }
}
