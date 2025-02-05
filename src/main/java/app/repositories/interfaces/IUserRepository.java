package app.repositories.interfaces;

import app.models.User;
import java.util.List;

public interface IUserRepository {
    void addUser(User user);
    User getUserById(String userId);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(String userId);
}