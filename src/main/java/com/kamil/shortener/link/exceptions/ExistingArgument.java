package com.kamil.shortener.link.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ExistingArgument extends IllegalArgumentException {

    public ExistingArgument(String s) {
        super(s);
    }

}
