package com.example.validation.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.example.validation.validator.YearMonthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = YearMonthValidator.class)
@Target(ElementType.FIELD)
@Retention(RUNTIME)
@NotBlank
public @interface YearMonth {

  String message() default "Invalid date format";

  String pattern() default "yyyyMMdd";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };

}
