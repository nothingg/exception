package com.example.exception.service;

import com.example.exception.entity.User;
import com.example.exception.exception.BaseException;
import com.example.exception.exception.UserException;
import com.example.exception.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String email , String password , String name) throws BaseException {
        //todo : สามารถใช้ Request Body ในการเช็คได้
        //validate
        if(Objects.isNull(email)){
            throw UserException.createEmailNull();
        }
        if(Objects.isNull(password)){
            throw UserException.createPasswordNull();
        }
        if(Objects.isNull(name)){
            throw UserException.createNameNull();
        }

        // Verify
        if(userRepository.existsByEmail(email)){
            throw UserException.createEmailDuplicate();
        }

        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setName(name);

        return userRepository.save(entity);
    }
}
