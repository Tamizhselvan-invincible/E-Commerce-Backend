package com.hetero.controller;


import com.hetero.models.Address;
import com.hetero.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/address/user/{userId}")
    public ResponseEntity<List<Address>> getAddressByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(addressService.fetchUserAddresses(userId));
    }

    @PostMapping("/address")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        return ResponseEntity.ok(addressService.addAddress(address));
    }

    @PutMapping("/address/{addressId}")
    public ResponseEntity<?> updateAddress(@RequestBody Integer addressId, @RequestBody boolean isSelected) {
     addressService.updateSelectedField(addressId,isSelected);
     return ResponseEntity.ok().build();
    }
}
