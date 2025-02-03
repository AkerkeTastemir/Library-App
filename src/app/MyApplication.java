package app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {

    private final IUserController userController;
    private final IBookController bookController;

    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(IUserController userController, IBookController bookController) {
        this.userController = userController;
        this.bookController = bookController;
    }

}
