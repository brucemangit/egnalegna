package com.solutionladder.ethearts.model.errorhandler;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.solutionladder.ethearts.persistence.entity.Member;
import com.solutionladder.ethearts.service.MemberService;

/**
 * Custom email validation. Check if the provided email is unique or not.
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Configurable
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private MemberService memberService;
    
    public UniqueEmailValidator() {}
    
    @Autowired
    public UniqueEmailValidator(MemberService memberService) {
        this.memberService = memberService;
    }
    
    public void initialize(UniqueEmail constraint) {
    }
 
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return false;
        }
        
        Optional<Member> member = this.memberService.findByEmail(email);
        return !member.isPresent();
    }
}