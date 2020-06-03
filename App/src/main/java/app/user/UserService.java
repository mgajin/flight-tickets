package app.user;

import java.util.List;

public class UserService {

    private static final UserDAO repository = new UserDAO();

    public static User getUser(String username) {
        return repository.getByUsername(username);
    }

    public static List<User> getUsers() {
        return repository.getUsers();
    }

    public static void addUser(User user) {
        repository.addUser(user);
    }

}
