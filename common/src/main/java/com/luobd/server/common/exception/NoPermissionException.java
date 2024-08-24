package com.luobd.server.common.exception;

public class NoPermissionException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NoPermissionException()
    {
        super("没有权限");
    }

    public NoPermissionException(String message)
    {
        super(message);
    }

    public NoPermissionException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NoPermissionException(Throwable cause)
    {
        super(cause);
    }
}
