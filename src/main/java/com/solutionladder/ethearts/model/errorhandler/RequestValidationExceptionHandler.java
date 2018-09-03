package com.solutionladder.ethearts.model.errorhandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Part of request that checks valiations 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@ControllerAdvice
@RestController
public class RequestValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidArgumentException.class)
    protected ResponseEntity<Object> handleInvalidArgumentException(InvalidArgumentException exception,
             WebRequest request) {
        List<String> messages = this.getValidationErrors(exception.getBindingResult());
        if (exception.getAdditionalMessages() != null) {
            messages.addAll(exception.getAdditionalMessages());
        }
        
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failure", 
                messages);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    
    private List<String> getValidationErrors(BindingResult bindingResult) {
        List<String> messages = new ArrayList<>();
        if (bindingResult != null) {
            for (ObjectError error : bindingResult.getAllErrors()) {
               messages.add(error.getDefaultMessage());
            }
        }
        return messages;
    }
}