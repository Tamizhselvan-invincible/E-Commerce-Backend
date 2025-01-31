package com.hetero.service;


import com.hetero.models.Address;

import java.util.List;

public interface AddressService {
    List<Address> fetchUserAddresses(Integer userId);

    void  updateSelectedField(Integer addressId, boolean selected);

    Address addAddress(Address address);
}
