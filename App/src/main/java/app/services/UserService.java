package app.services;

import app.database.Database;
import app.models.User;

public class UserService {

    private static final Object LOCK = new Object();

    //    Get user from database by username
    public static User getUser(String username) {

        synchronized (LOCK) {
            for (User user : Database.getUsers()) {
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
        }

        return null;
    }

}
