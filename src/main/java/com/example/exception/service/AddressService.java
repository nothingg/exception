package com.example.exception.service;

import com.example.exception.entity.Address;
import com.example.exception.entity.Social;
import com.example.exception.entity.User;
import com.example.exception.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository ;

    public AddressService( AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findByUser(User user){
        return addressRepository.findByUser(user);
    }

    public Address createAddress(User user , String line1 , String line2 , String zipcode ){
        Address address = new Address();
        address.setLine1(line1);
        address.setLine2(line2);
        address.setZipcode(zipcode);
        address.setUser(user);

        return addressRepository.save(address);
    }

}
