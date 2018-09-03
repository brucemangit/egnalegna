package com.solutionladder.ethearts.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class AuthenticationRequest {

    @Email(message="Invalid Email provided")
    private String email;

    @NotEmpty(message="Password cannot be null")
    private String password;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}