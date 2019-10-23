package com.example.demo.jwt.exception;
/**
 * @decription：自定义异常
 * @author :Trin
 * @date:2019/10/14
 */
public class TokenIsExpiredException extends Exception{

    public TokenIsExpiredException(){
    }

    public TokenIsExpiredException(String message){
        super(message);
    }

    public TokenIsExpiredException(String message,Throwable cause){
        super(message,cause);
    }

    public TokenIsExpiredException(Throwable cause){
        super(cause);
    }

    public TokenIsExpiredException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace){
        super(message,cause,enableSuppression,writableStackTrace);
    }
}
