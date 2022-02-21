package com.example.exception.business;

import com.example.exception.entity.User;
import com.example.exception.exception.BaseException;
import com.example.exception.exception.FileException;
import com.example.exception.mapper.UserMapper;
import com.example.exception.model.MRegisterRequest;
import com.example.exception.model.MRegisterResponse;
import com.example.exception.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UserBusiness {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public MRegisterResponse register(MRegisterRequest request) throws BaseException {

        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());

        return userMapper.userDtoToMRegisterResponse(user);

    }

    public String uploadProfilePicture(MultipartFile file) throws BaseException {
        if(file == null){
            throw FileException.fileNull();
        }

        if(file.getSize() > 1048576 * 2 ){
            throw FileException.filMaxSize();
        }
        String contentType = file.getContentType();
        if(contentType == null){
            throw FileException.unsuppported();
        }

        List<String> supportedType = Arrays.asList("image/jpeg","image/png");
        if(!supportedType.contains(contentType)){
            throw FileException.unsuppported();
        }

        //Todo : upload file to FTP Server
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
