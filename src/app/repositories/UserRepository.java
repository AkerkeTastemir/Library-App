package app.repositories;

import app.repositories.interfaces.IUserRepository;
import app.models.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository implements IUserRepository {
    private final Map<String, User> users; // Simulating a database with a Map

    public UserRepository() {
        this.users = new HashMap<>();
    }

    @Override
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public User getUserById(String userId) {
        return users.get(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void updateUser(User user) {
        if (users.containsKey(user.getUserId())) {
            users.put(user.getUserId(), user);
        }
    }

    @Override
    public void deleteUser(String userId) {
        users.remove(userId);
    }
}