package com.solutionladder.ethearts.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * This is custom user for the security. The custom is need to add more
 * elements to it to get those later in controllers than the usual username and password only
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 * @date Aug 19 2018
 */
public class CustomUser extends org.springframework.security.core.userdetails.User{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Long memberId;
    
    private String firstName;
    
    private String lastName;
    
    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        // TODO Auto-generated constructor stub
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

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
    
    @Override
    public String toString() {
        return "MemberId : " + memberId + "First name " + firstName + " Last name " + lastName;
    }
}
