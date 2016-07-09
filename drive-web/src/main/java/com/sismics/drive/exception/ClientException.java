package com.sismics.drive.exception;

import com.sun.deploy.util.SessionState;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class ClientException extends Exception {
    public ClientException(String message) {
        super(message);
    }
}
