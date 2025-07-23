package br.com.dio.validator;

import br.com.dio.exception.ValidatorException;
import br.com.dio.model.ServiceModel;

import java.util.regex.Pattern;

public class ServiceValidator {

    private ServiceValidator() {

    }

    public static void verifyModel(final ServiceModel model) throws ValidatorException {
        if (stringIsBlank(model.getName()))
            throw new ValidatorException("Informe um nome válido!");
        if (model.getName().length() <= 1)
            throw new ValidatorException("O nome deve ter mais que 1 caractere.");
        }

    private static boolean stringIsBlank(final String value) {
        return value == null || value.isBlank();
    }
}