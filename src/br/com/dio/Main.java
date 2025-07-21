package br.com.dio;

import br.com.dio.dao.UserDAO;
import br.com.dio.exception.EmptyStorageException;
import br.com.dio.exception.UserNotFoundException;
import br.com.dio.exception.ValidatorException;
import br.com.dio.model.UserModel;
import br.com.dio.model.MenuOption;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static br.com.dio.validator.UserValidator.verifyModel;

public class Main {

    public static final UserDAO dao = new UserDAO();
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n->>>>       SISTEMA DE CADASTRO DE USUÁRIOS       <<<<-\n" +
                    "Bem vindo! Por gentileza selecione a operação desejada: \n\n" +
                    "1 - Cadastrar\n" +
                    "2 - Atualizar\n" +
                    "3 - Excluir\n" +
                    "4 - Buscar pelo ID\n" +
                    "5 - Listar todos\n" +
                    "6 - Sair");

            int userInput = scanner.nextInt();
            scanner.nextLine();

            MenuOption selectedOption = MenuOption.values()[userInput - 1];

            switch (selectedOption) {
                case SAVE -> {
                    try {var user = dao.save(requestToSave());
                    System.out.printf("Usuário %s cadastrado com sucesso! (ID: %d)%n\n" +
                            "======================================================= ", user.getName(), user.getId());
                    }catch (ValidatorException ex){
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                }
                case UPDATE -> {
                    try{
                        var user = dao.update(requestToUpdate());
                        System.out.printf("Usuário atualizado: %s%n\n" +
                                "=======================================================", user);
                    }catch (UserNotFoundException | EmptyStorageException ex){
                        System.out.println(ex.getMessage());
                    } catch (ValidatorException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }finally {
                        System.out.println("");
                    }
                }
                case DELETE -> {
                    try{
                        dao.delete(requestId());
                        System.out.println("Usuário excluído!");
                    }catch (UserNotFoundException | EmptyStorageException ex){
                        System.out.println(ex.getMessage());
                    }finally {
                        System.out.println("=======================================================");
                    }

                }
                case FIND_BY_ID -> {
                    try{
                        var id = requestId();
                        var user = dao.findById(id);
                        System.out.printf("Usuário com ID %d: %s%n", id, user);
                    }catch (UserNotFoundException | EmptyStorageException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                case FIND_ALL -> {
                    var users = dao.findAll();
                    if (users.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado!\n" +
                                "=======================================================");
                    } else {
                        System.out.println("Usuários cadastrados: \n");
                        users.forEach(user -> System.out.printf("- %s (ID: %d)%n", user.getName(), user.getId()));
                        System.out.println("=======================================================");
                    }
                }
                case EXIT -> System.exit(0);
            }
        }
    }

    private static long requestId() {
        System.out.print("Informe o ID do usuário: ");
        return scanner.nextLong();
    }

    private static UserModel requestToSave()throws ValidatorException{
        System.out.print("Nome: ");
        var name = scanner.next();
        System.out.print("Email: ");
        var email = scanner.next();
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        var birthdayString = scanner.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = LocalDate.parse(birthdayString, formatter);
        var user = new UserModel(0, name, email, birthday);
        verifyModel(user);
        return user;
    }

    private static UserModel validateInputs(final long id, final String name,
                                            final String email, final LocalDate birthday) throws ValidatorException{
        var user = new UserModel(0, name, email, birthday);
        verifyModel(user);
        return user;
    }

    private static UserModel requestToUpdate() throws ValidatorException {
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
        UserModel model= new UserModel(id, name, email, birthday);
        verifyModel(model);
        try{
            model = validateInputs(0, name, email, birthday);
        }catch (ValidatorException ex){
        }
        return model; }
}
