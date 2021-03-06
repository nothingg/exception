package com.example.exception.service;

import com.example.exception.entity.User;
import com.example.exception.exception.BaseException;
import com.example.exception.exception.UserException;
import com.example.exception.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }

    public User update(User user){
        return userRepository.save(user);
    }

    public User updateName(String id , String name) throws BaseException {
        Optional<User> byId = userRepository.findById(id);
        if(!byId.isPresent()){
            throw UserException.notFound();
        }
        User user = byId.get();
        user.setName(name);

        return userRepository.save(user);
    }

    public void deleteById(String id){
        userRepository.deleteById(id);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword){
        return passwordEncoder.matches(rawPassword,encodedPassword);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
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
        entity.setPassword(passwordEncoder.encode(password));
        entity.setName(name);

        return userRepository.save(entity);
    }
}
