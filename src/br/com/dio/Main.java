package br.com.dio;

import br.com.dio.dao.UserDAO;
import br.com.dio.model.UserModel;
import br.com.dio.model.MenuOption;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static final UserDAO dao = new UserDAO();
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nBem vindo ao cadastro de usuários\n" +
                    "Selecione a operação desejada: \n" +
                    "1 - Cadastrar\n" +
                    "2 - Atualizar\n" +
                    "3 - Excluir\n" +
                    "4 - Buscar pelo ID\n" +
                    "5 - Listar todos\n" +
                    "6 - Sair");

            int userInput = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer do Enter

            MenuOption selectedOption = MenuOption.values()[userInput - 1];

            switch (selectedOption) {
                case SAVE -> {
                    var user = dao.save(requestToSave());
                    System.out.printf("Usuário cadastrado: %s %n", user);
                }
                case UPDATE -> {
                    var user = dao.update(requestToUpdate());
                    System.out.printf("Usuário atualizado: %s%n", user);
                }
                case DELETE -> {
                    dao.delete(requestId());
                    System.out.println("Usuário excluído!");
                }
                case FIND_BY_ID -> {
                    var id = requestId();
                    var user = dao.findById(id);
                    System.out.printf("Usuário com ID %d: %s%n", id, user);
                }
                case FIND_ALL -> {
                    var users = dao.findAll();
                    System.out.println("Usuários cadastrados: ");
                    users.forEach(System.out::println);
                }
                case EXIT -> System.exit(0);
            }
        }
    }

    private static long requestId() {
        System.out.print("Informe o ID do usuário: ");
        return scanner.nextLong();
    }

    private static UserModel requestToSave() {
        System.out.print("Nome: ");
        var name = scanner.next();
        System.out.print("Email: ");
        var email = scanner.next();
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        var birthdayString = scanner.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = LocalDate.parse(birthdayString, formatter).atStartOfDay().atOffset(ZoneOffset.UTC);
        return new UserModel(0, name, email, birthday);
    }

    private static UserModel requestToUpdate() {
        System.out.print("ID: ");
        var id = scanner.nextLong();
        System.out.print("Nome: ");
        var name = scanner.next();
        System.out.print("Email: ");
        var email = scanner.next();
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        var birthdayString = scanner.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = LocalDate.parse(birthdayString, formatter).atStartOfDay().atOffset(ZoneOffset.UTC);
        return new UserModel(id, name, email, birthday);
    }
}
