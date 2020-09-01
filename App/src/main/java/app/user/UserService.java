package app.user;

import java.util.List;

public class UserService {

    private final UserDAO repository = new UserDAO();

    public User getUser(String username) {
        return repository.getByUsername(username);
    }

    public List<User> getUsers() {
        return repository.getUsers();
    }

    public boolean addUser(User user) {
        return repository.insert(user);
    }

    public boolean updateUser(User user) {
        return repository.update(user);
    }

}
