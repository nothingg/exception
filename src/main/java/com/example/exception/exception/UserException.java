package com.example.exception.exception;

public class UserException extends BaseException {

    public UserException(String code) {
        super("user."+code);
    }


    public static UserException requestNull(){
        return new UserException("register.request.null");
    }

    public static UserException emailNull(){
        return new UserException("register.email.null");
    }

    public static UserException createEmailNull(){
        return new UserException("create.email.null");
    }

    public static UserException createPasswordNull(){
        return new UserException("create.password.null");
    }

    public static UserException createNameNull(){
        return new UserException("crate.Name.null");
    }

    public static UserException createEmailDuplicate() { return new UserException("crate.email.duplicate");   }
}
