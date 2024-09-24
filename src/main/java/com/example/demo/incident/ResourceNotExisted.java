package com.example.demo.incident;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotExisted extends RuntimeException {
    public ResourceNotExisted(String message) {
        super(message);
    }
}
