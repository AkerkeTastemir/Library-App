package starter;

import app.MyApplication;
import app.data.PostgresDB;
import app.data.interfaces.IDB;
import app.repositories.BookRepository;
import app.repositories.UserRepository;
import app.repositories.interfaces.IBookRepository;
import app.repositories.interfaces.IUserRepository;
import app.controllers.BookController;
import app.controllers.UserController;
import app.controllers.interfaces.IBookController;
import app.controllers.interfaces.IUserController;

public class Main {

    public static void main(String[] args) {

        IDB db = new PostgresDB("jdbc:postgresql://34.118.52.174:5432", "akerke", "0000", "assignment");
        db.connect();

        // repositories
        IUserRepository userRepository = new UserRepository(db);
        IBookRepository bookRepository = new BookRepository(db);

        // controllers
        IUserController userController = new UserController(userRepository);
        IBookController bookController = new BookController(bookRepository);

        // starts
        Application app = new MyApplication(userController, bookController);
        app.start();

        // ends
        db.close();
    }
}