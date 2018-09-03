package com.solutionladder.ethearts.model.response;

import java.util.List;

/**
 * Class to have generic response type.
 * Basically it will contain, Object, message and success.
 * The object will be whatever we are returning and message could be empty incase of 
 * valid response.
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
public class GenericResponse {
    /**
     * An object that will be retuned if there is any
     */
    private Object object;
    
    /**
     * List of messages mostly in case of failure
     */
    private List<String> messages;
    
    /**
     * Is all went fine or not?
     */
    private boolean success;
    
    public GenericResponse() {}
    
    public GenericResponse(Object object, List<String> messages, boolean success) {
        this.object = object;
        this.messages = messages;
        this.success = success;
    }
    
    public Object getObject() {
        return object;
    }
    public void setObject(Object object) {
        this.object = object;
    }
    public List<String> getMessages() {
        return messages;
    }
    public void setMessage(List<String> messages) {
        this.messages = messages;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    
}
