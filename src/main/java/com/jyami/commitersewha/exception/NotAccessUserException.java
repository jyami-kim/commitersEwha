package com.jyami.commitersewha.exception;

/**
 * Created by jyami on 2020/09/30
 */
public class NotAccessUserException extends RuntimeException{
    public NotAccessUserException() {
        super();
    }

    public NotAccessUserException(String message) {
        super(message);
    }
}
