package br.com.dio;

import br.com.dio.model.ServiceModel;
import br.com.dio.register.UserRegister;
import br.com.dio.exception.EmptyStorageException;
import br.com.dio.exception.NotFoundException;
import br.com.dio.exception.ValidatorException;
import br.com.dio.model.UserModel;
import br.com.dio.model.MenuOption;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static br.com.dio.validator.UserValidator.verifyModel;

public class Main {

    public static final UserRegister dao = new UserRegister();
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {

            System.out.println("\n->>>>     SISTEMA DE CADASTRO DE USUÁRIOS E SERVIÇOS    <<<<-\n" +
                    "Selecione seu tipo de cadastro: \n\n" +
                    "1 - Usuários" +
                    "2 - Serviços");

            int menuInput = scanner.nextInt();

            if (menuInput == 1) {

                while (true) {
                    System.out.println("\n->>>>       SISTEMA DE CADASTRO DE USUÁRIOS       <<<<-\n" +
                            "Por gentileza selecione a operação desejada: \n\n" +
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
                            try {
                                var user = dao.save(requestToSave());
                                System.out.printf("Usuário %s cadastrado com sucesso! (ID: %d)%n\n" +
                                        "======================================================= ", user.getName(), user.getId());
                            } catch (ValidatorException ex) {
                                System.out.println(ex.getMessage());
                                ex.printStackTrace();
                            }
                        }
                        case UPDATE -> {
                            try {
                                var user = dao.update(requestToUpdate());
                                System.out.printf("Usuário atualizado: %s%n\n" +
                                        "=======================================================", user);
                            } catch (NotFoundException | EmptyStorageException ex) {
                                System.out.println(ex.getMessage());
                            } catch (ValidatorException ex) {
                                System.out.println(ex.getMessage());
                                ex.printStackTrace();
                            } finally {
                                System.out.println("");
                            }
                        }
                        case DELETE -> {
                            try {
                                dao.delete(requestId());
                                System.out.println("Usuário excluído!");
                            } catch (NotFoundException | EmptyStorageException ex) {
                                System.out.println(ex.getMessage());
                            } finally {
                                System.out.println("=======================================================");
                            }

                        }
                        case FIND_BY_ID -> {
                            try {
                                var id = requestId();
                                var user = dao.findById(id);
                                System.out.printf("Usuário com ID %d: %s%n", id, user);
                            } catch (NotFoundException | EmptyStorageException ex) {
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
            } else {
                while (true) {
                    System.out.println("\n->>>>       SISTEMA DE CADASTRO DE SERVIÇOS       <<<<-\n" +
                            "Por gentileza selecione a operação desejada: \n\n" +
                            "1 - Cadastrar\n" +
                            "2 - Atualizar\n" +
                            "3 - Excluir\n" +
                            "4 - Buscar pelo ID\n" +
                            "5 - Listar todos\n" +
                            "6 - Sair");

                    int ServiceInput = scanner.nextInt();
                    scanner.nextLine();

                    MenuOption selectedOption = MenuOption.values()[ServiceInput - 1];

                    switch (selectedOption) {
                        case SAVE -> {
                            try {
                                var service = dao.save(requestToSave());
                                System.out.printf("Serviço %s cadastrado com sucesso! (ID: %d)%n\n" +
                                        "======================================================= ", service.getName(), service.getId());
                            } catch (ValidatorException ex) {
                                System.out.println(ex.getMessage());
                                ex.printStackTrace();
                            }
                        }
                        case UPDATE -> {
                            try {
                                var user = dao.update(requestToUpdate());
                                System.out.printf("Serviço atualizado: %s%n\n" +
                                        "=======================================================", service);
                            } catch (NotFoundException | EmptyStorageException ex) {
                                System.out.println(ex.getMessage());
                            } catch (ValidatorException ex) {
                                System.out.println(ex.getMessage());
                                ex.printStackTrace();
                            } finally {
                                System.out.println("");
                            }
                        }
                        case DELETE -> {
                            try {
                                dao.delete(requestId());
                                System.out.println("Serviço excluído!");
                            } catch (NotFoundException | EmptyStorageException ex) {
                                System.out.println(ex.getMessage());
                            } finally {
                                System.out.println("=======================================================");
                            }

                        }
                        case FIND_BY_ID -> {
                            try {
                                var id = requestId();
                                var service = dao.findById(id);
                                System.out.printf("Serviço com ID %d: %s%n", id, service);
                            } catch (NotFoundException | EmptyStorageException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                        case FIND_ALL -> {
                            var services = dao.findAll();
                            if (services.isEmpty()) {
                                System.out.println("Nenhum serviço cadastrado!\n" +
                                        "=======================================================");
                            } else {
                                System.out.println("Usuários cadastrados: \n");
                                services.forEach(user -> System.out.printf("- %s (ID: %d)%n", user.getName(), user.getId()));
                                System.out.println("=======================================================");
                            }
                        }
                        case EXIT -> System.exit(0);
                    }
                }
            }
        }
