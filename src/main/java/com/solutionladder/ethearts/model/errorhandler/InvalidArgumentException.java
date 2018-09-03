package com.solutionladder.ethearts.model.errorhandler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception interceptor
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String message;
    private BindingResult bindingResult;
    private List<String> additionalMessages;

    public InvalidArgumentException(String message, BindingResult bindingResult, List<String> messages) {
        super(message);
        this.message = message;
        this.bindingResult = bindingResult;
        this.additionalMessages = messages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
    
    /**
     * For messages that are outside of the BindingResult, handle those in additional messages.
     * @param messages
     */
    public void setAdditionalMessages(List<String> messages) {
        this.additionalMessages = messages;
    }
    
    public List<String> getAdditionalMessages() {
        return this.additionalMessages;
    }
 }
