package com.avaliacao.agendatransacoes.validation;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckDateValidator implements ConstraintValidator<CheckDateFormat, String> {

    @Override
    public void initialize(CheckDateFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String dateStr, ConstraintValidatorContext constraintContext) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.US)
                .withResolverStyle(ResolverStyle.STRICT);
            dateFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}