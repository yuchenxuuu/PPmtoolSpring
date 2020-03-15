package io.ppmdev.ppmtool.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNameExceptions extends RuntimeException{

    private String message;
    public ProjectNameExceptions(String message){
        super(message);
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }


}

