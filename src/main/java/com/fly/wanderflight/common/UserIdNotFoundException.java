package com.fly.wanderflight.common;

public class UserIdNotFoundException extends  RuntimeException{
    public UserIdNotFoundException(String message){
        super(message);
    }
}
