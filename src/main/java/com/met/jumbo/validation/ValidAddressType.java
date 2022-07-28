package com.met.jumbo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Target( {ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AddressTypeValidator.class)
public @interface ValidAddressType {
    //error message
    String message() default "Invalid Address Type: must be ZIPCODE or CITY";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}