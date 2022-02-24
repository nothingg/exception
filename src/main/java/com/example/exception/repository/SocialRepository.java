package com.example.exception.repository;

import com.example.exception.entity.Social;
import com.example.exception.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SocialRepository extends CrudRepository<Social, String> {


    Optional<Social> findByUser(User user);

}
