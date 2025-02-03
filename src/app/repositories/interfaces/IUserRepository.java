package app.repositories.interfaces;

import app.models.User;
import java.util.List;

public interface IUserRepository {
    void addUser(User user);
    User getUserById(String userId);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(String userId);
}