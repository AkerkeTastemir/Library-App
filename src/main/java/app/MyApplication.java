package app;

import app.controllers.interfaces.IBookController;
import app.controllers.interfaces.IUserController;
import app.menu.AuthMenu;
import app.menu.interfaces.IMenu;
import app.models.User;
import starter.Application;

import java.util.Scanner;

public class MyApplication implements Application {

    private static MyApplication instance;

    private final IUserController userController;
    private final IBookController bookController;

    private final Scanner scanner = new Scanner(System.in);

    private IMenu currentMenu;
    private User user;

    public MyApplication(IUserController userController, IBookController bookController) {
        this.userController = userController;
        this.bookController = bookController;
    }

    @Override
    public void start() {

        instance = this;

        changeMenu(new AuthMenu());

    }

    public void changeMenu(IMenu menu) {
        currentMenu = menu;
        currentMenu.onEnter();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public IUserController getUserController() {
        return userController;
    }

    public IBookController getBookController() {
        return bookController;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public IMenu getCurrentMenu() {
        return currentMenu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
