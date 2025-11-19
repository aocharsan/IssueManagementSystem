package com.growvenus.ims.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IssueAlreadyExistsException extends RuntimeException{
    public IssueAlreadyExistsException(String msg) {
        super(msg);
    }
}
