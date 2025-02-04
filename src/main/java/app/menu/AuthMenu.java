package app.menu;

import app.MyApplication;
import app.menu.interfaces.IMenu;
import app.models.User;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class AuthMenu implements IMenu {
    private final MyApplication app = MyApplication.getInstance();
    private final Scanner scanner = app.getScanner();

    @Override
    public void onEnter() {
        lobby();
    }

    public void lobby() {

        System.out.println("-=-=-=-=-=-=-=-=-=-");
        System.out.println();
        System.out.println("[!] Auth Menu");
        System.out.println();
        System.out.println("1. Register");
        System.out.println("2. Log-in");
        System.out.println();
        System.out.print("Select a option: ");

        while (true) {

            int option = selectOption();

            switch (option) {

                case 1 -> {
                    register();
                    break;
                }

                case 2 -> {
                    login();
                    break;
                }

                default -> {
                    System.out.println("Option not found. Try again.");
                }

            }

        }

    }

    public void register() {

        System.out.println();
        System.out.println("===- Register -===");
        System.out.println();

        System.out.print("Your name: ");
        String name = scanner.next();

        System.out.print("Your email: ");
        String email = scanner.next();

        System.out.print("Your password: ");
        String password = scanner.next();

        User user = new User(
                UUID.randomUUID().toString(), name, email, password
        );
        app.getUserController().addUser(user);

        System.out.println();
        System.out.println("Your user created!");
        System.out.println();

        lobby();
    }

    public void login() {

        System.out.println();
        System.out.println("===- Login -===");
        System.out.println();

        System.out.print("Your email: ");
        String email = scanner.next();

        System.out.print("Your password: ");
        String password = scanner.next();

        // todo search user and match password

        System.out.println();
        System.out.println("Success!");
        System.out.println();

        // todo switch to user menu
    }

    public int selectOption() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That option is not a num");
            System.out.print("Select a option: ");
            scanner.nextLine();
            return -1;
        }
    }


}
