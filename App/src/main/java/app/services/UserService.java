package app.services;

import app.database.Database;
import app.models.User;

public class UserService {

    //    Get user from database by username
    public static User getUser(String username) {

        for (User user : Database.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

}
