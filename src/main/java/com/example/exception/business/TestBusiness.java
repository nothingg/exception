package com.example.exception.business;

import com.example.exception.exception.BaseException;
import com.example.exception.model.MRegisterRequest;

import java.util.Objects;

public class TestBusiness {


    public String register(MRegisterRequest request) throws BaseException {
        if (request == null) {
            throw UserException.requestNull();
        }

        if (Objects.isNull(request.getEmail())) {
            throw UserException.emailNull();
        }

        return "";
    }
}
