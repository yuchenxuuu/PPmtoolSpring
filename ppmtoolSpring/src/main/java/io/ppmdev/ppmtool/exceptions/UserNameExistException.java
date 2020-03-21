package io.ppmdev.ppmtool.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNameExistException extends RuntimeException{

    public UserNameExistException(String message){ super(message);}
}
