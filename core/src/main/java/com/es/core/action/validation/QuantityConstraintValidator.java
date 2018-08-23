package com.es.core.action.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class QuantityConstraintValidator implements ConstraintValidator<Quantity, String> {
    @Override
    public void initialize(Quantity constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
