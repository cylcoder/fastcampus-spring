package com.example.validation.annotation;

import com.example.validation.validator.YearMonthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YearMonthValidator.class)
@NotBlank
public @interface YearMonth {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "날짜 형식이 올바르지 않습니다. ex) 20240816";

    String pattern() default "yyyyMM";

}
