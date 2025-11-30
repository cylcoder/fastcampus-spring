package com.example.validation.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.example.validation.validator.PhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneNumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface PhoneNumber {

  String message() default "Invalid phone number format";

  String regexp() default "^\\d{2,3}-\\d{3,4}-\\d{4}$";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };

}
