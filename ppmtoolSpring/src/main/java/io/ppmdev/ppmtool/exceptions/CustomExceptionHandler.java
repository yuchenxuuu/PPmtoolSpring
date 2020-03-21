package io.ppmdev.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleException(ProjectIdExceptions pie, WebRequest request){
            ProjectIdExceptionResponse response = new ProjectIdExceptionResponse(pie.getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> userNameExistHandler(UserNameExistException pie, WebRequest request){
        UserNameExistResponse response = new UserNameExistResponse(pie.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

}
