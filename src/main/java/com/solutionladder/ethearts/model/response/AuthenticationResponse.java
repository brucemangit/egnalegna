package com.solutionladder.ethearts.model.response;

/**
 * Response for authentication
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
public class AuthenticationResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String token;

    public AuthenticationResponse(String firstName, String lastName, String email, String token) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
        this.email = email;
    }

    public AuthenticationResponse() {
        this.token = "";
        this.email = "";
        this.firstName = "";
        this.lastName = "";
    }

    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getToken() {
        return this.token;
    }

    public String getEmail() {
        return this.email;
    }
}