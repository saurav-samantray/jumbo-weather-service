package com.met.jumbo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class AddressTypeValidator implements ConstraintValidator<ValidAddressType, String> {
    @Override
    public boolean isValid(String addressType, ConstraintValidatorContext constraintValidatorContext) {
        return List.of("ZIPCODE","CITY").contains(addressType);
    }
}
