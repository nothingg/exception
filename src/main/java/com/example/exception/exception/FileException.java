package com.example.exception.exception;

public class FileException extends BaseException {

    public FileException(String code) {
        super("file."+code);
    }


    public static FileException fileNull(){
        return new FileException("null");
    }

    public static FileException filMaxSize(){
        return new FileException("max.size");
    }

    public static FileException unsuppported(){
        return new FileException("unsuppported.file.type");
    }

}
