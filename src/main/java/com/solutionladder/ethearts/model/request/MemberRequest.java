package com.solutionladder.ethearts.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Check for the better approach for this. This is another version of member
 * entity only used for the request puposes Especially - during the edit where
 * other constraints should not hold.
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
public class MemberRequest {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Email
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
