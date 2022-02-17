package com.example.exception.business;

import com.example.exception.exception.BaseException;
import org.apache.catalina.User;

public class UserException extends BaseException {

    public UserException(String code) {
        super("user."+code);
    }


    public static UserException requestNull(){
        return new UserException("register.request.null");
    }

    //user.register.email.null

    public static UserException emailNull(){
        return new UserException("register.email.null");
    }
}
