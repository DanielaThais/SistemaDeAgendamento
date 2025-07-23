package br.com.dio.util;

import br.com.dio.exception.ValidatorException;
import br.com.dio.model.ServiceModel;
import br.com.dio.model.UserModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static br.com.dio.validator.UserValidator.verifyModelUser;

public class InputUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static long requestId() {
        System.out.print("Informe o ID: ");
        return scanner.nextLong();
    }

    public static String requestName() {
        System.out.print("Informe o nome: ");
        return scanner.next();
    }

    private static UserModel requestToSaveUser() throws ValidatorException {
        var name = requestName();
        System.out.print("Email: ");
        var email = scanner.next();
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        var birthdayString = scanner.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = LocalDate.parse(birthdayString, formatter);
        var user = new UserModel(0, name, email, birthday);
        verifyModelUser(user);
        return user;
    }

    private static UserModel validateInputsUser(final long id, final String name,
                                                final String email, final LocalDate birthday) throws ValidatorException {
        var user = new UserModel(0, name, email, birthday);
        verifyModelUser(user);
        return user;
    }

    private static UserModel requestToUpdateUser() throws ValidatorException {
        System.out.print("ID: ");
        var id = scanner.nextLong();
        System.out.print("Nome: ");
        var name = scanner.next();
        System.out.print("Email: ");
        var email = scanner.next();
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        var birthdayString = scanner.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = LocalDate.parse(birthdayString, formatter);
        UserModel model = new UserModel(id, name, email, birthday);
        verifyModelUser(model);
        try {
            model = validateInputs(0, name, email, birthday);
        } catch (ValidatorException ex) {
        }
        return model;
    }
    private static ServiceModel requestToSaveService()throws ValidatorException{
        var name = requestName();
        System.out.print("Descrição: ");
        var description = scanner.next();
        System.out.print("Valor: ");
        var price = scanner.next();
        var service = new ServiceModel(0, name, description, price);
        return service;
    }

    private static ServiceModel validateInputsUser(final long id, final String name,
                                                   final String description, BigDecimal price) throws ValidatorException {
        var user = new UserModel(0, name, description, price);
        verifyModelService(service);
        return service;
    }
}