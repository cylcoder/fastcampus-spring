package com.example.validation.validator;

import com.example.validation.annotation.YearMonth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

  private String pattern;

  @Override
  public void initialize(YearMonth constraintAnnotation) {
    pattern = constraintAnnotation.pattern();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    String newValue = value + "01";
    String newPattern = pattern + "dd";

    try {
      LocalDate date = LocalDate.parse(newValue, DateTimeFormatter.ofPattern(newPattern));
      System.out.println("date = " + date);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
