package app.menu;

import app.MyApplication;
import app.menu.interfaces.IMenu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu implements IMenu {

    private final MyApplication app = MyApplication.getInstance();
    private final Scanner scanner = app.getScanner();

    @Override
    public void onEnter() {
        menu();
    }

    public void menu() {

        System.out.println("-=-=-=-=-=-=-=-=-=-");
        System.out.println();
        System.out.println("[#] Main Menu");
        System.out.println();
        System.out.println("  1. My Books");
        System.out.println("  2. My Fines");
        System.out.println("  3. Order Book");
        System.out.println("  4. Return Book");
        System.out.println();
        System.out.print("Select a option: ");

        while (true) {

            int option = selectOption();

            switch (option) {

                default -> {
                    System.out.println();
                    System.out.println("Option not found. Try again.");
                }

            }

        }

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
