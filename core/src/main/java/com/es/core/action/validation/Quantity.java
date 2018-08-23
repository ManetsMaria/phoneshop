package com.es.core.action.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = QuantityConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Quantity {
    String message() default "{Quantity}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
