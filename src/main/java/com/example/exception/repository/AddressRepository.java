package com.example.exception.repository;

import com.example.exception.entity.Address;
import com.example.exception.entity.Social;
import com.example.exception.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, String> {


    List<Address> findByUser(User user);

}
