package com.spring.microservices.exceptions;

import com.spring.microservices.ui.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleAnyException(Exception ex, WebRequest request){

        String errorMessage = "";
        if(ex.getLocalizedMessage() == null){
            errorMessage = ex.toString();
        }else{
            errorMessage = ex.getLocalizedMessage();
        }
        return new ResponseEntity( new ErrorMessage(new Date(), errorMessage),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
    public ResponseEntity handleSpecificExceptions(Exception ex, WebRequest request){

        String errorMessage = "";
        if(ex.getLocalizedMessage() == null){
            errorMessage = ex.toString();
        }else{
            errorMessage = ex.getLocalizedMessage();
        }
        return new ResponseEntity( new ErrorMessage(new Date(), errorMessage),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
