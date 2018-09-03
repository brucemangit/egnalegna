package com.solutionladder.ethearts.model.errorhandler;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Configurable;

/**
 * Custom email validation. Check if the provided email is unique or not.
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Configurable
public class MinimumAgeValidator implements ConstraintValidator<MinimumAge, LocalDate> {

    protected long minAge;
    
    @Override
    public void initialize(MinimumAge ageValue) {
        this.minAge = ageValue.value();
    }
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {

        if ( date == null ) {
            return false;
        }

        LocalDate today = LocalDate.now();
        return ChronoUnit.YEARS.between(date, today)>=minAge;
    }
}