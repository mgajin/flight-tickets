package app.user;

import app.database.Dao;

import java.util.List;

public class UserService {

    private final Dao<User> repository;

    public UserService(Dao<User> repository) {
        this.repository = repository;
    }

    public User getUser(String username) {
        return repository.find(username);
    }

    public List<User> getUsers() {
        return repository.getAll();
    }

    public boolean addUser(User user) {
        return repository.insert(user);
    }

    public boolean updateUser(User user) {
        return repository.update(user);
    }
}
