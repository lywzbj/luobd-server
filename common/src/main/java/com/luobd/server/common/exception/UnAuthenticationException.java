package com.luobd.server.common.exception;

public class UnAuthenticationException extends RuntimeException{


    public UnAuthenticationException() {
        super("Not Authentication");
    }

}
