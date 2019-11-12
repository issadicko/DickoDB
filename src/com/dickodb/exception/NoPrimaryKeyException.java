package com.woodi.orm.exception;

public class NoPrimaryKeyException extends Exception{

    private final String message;

    public NoPrimaryKeyException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
