package br.com.dio.validator;

import br.com.dio.exception.ValidatorException;
import br.com.dio.model.UserModel;
import java.util.regex.Pattern;

public class UserValidator {

    private UserValidator() {

    }

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


    public static void verifyModelUser(final UserModel model) throws ValidatorException {
        if (stringIsBlank(model.getName()))
            throw new ValidatorException("Informe um nome válido!");
        if (model.getName().length() <= 1)
            throw new ValidatorException("O nome deve ter mais que 1 caractere.");
        if(stringIsBlank(model.getEmail()))
            throw new ValidatorException("Informe um endereço de e-mail válido!");
        if (!EMAIL_PATTERN.matcher(model.getEmail()).matches()) {
            throw new ValidatorException("Informe um endereço de e-mail válido!");
        }

        final int MIN_YEAR = 1940;
        final int MAX_YEAR = 2025;

        if (model.getBirthday() == null) {
            throw new ValidatorException("Informe uma data de nascimento válida!");
        }

        int birthYear = model.getBirthday().getYear();

        if (birthYear < MIN_YEAR || birthYear > MAX_YEAR) {
            throw new ValidatorException("O ano de nascimento deve ser entre " + MIN_YEAR + " e " + MAX_YEAR + ".");
        }
    }

    private static boolean stringIsBlank(final String value) {
        return value == null || value.isBlank();
    }
}