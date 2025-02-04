package app.controllers.interfaces;

import app.models.User;

import java.util.List;

public interface IUserController {
    void addUser(User user);
    User getUserById(String userId);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(String userId);

    String registerUser(String name, String surname, String role);

    String getRole(int userId);
}