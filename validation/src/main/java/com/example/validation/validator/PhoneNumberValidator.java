package com.example.validation.validator;

import com.example.validation.annotation.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

  private String regex;

  @Override
  public void initialize(PhoneNumber constraintAnnotation) {
    regex = constraintAnnotation.regexp();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return Pattern.matches(regex, value);
  }

}
