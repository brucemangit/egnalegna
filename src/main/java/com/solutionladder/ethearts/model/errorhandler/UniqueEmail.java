package com.solutionladder.ethearts.model.errorhandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom email validation
 * Checks if the email already exists or not.
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "{Email already exists}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}