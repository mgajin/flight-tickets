package app.user;

import app.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final Connection connection = Database.getConnection();

    public void addUser(User user) {
        String query = "INSERT INTO users (username, password) values (?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public User getByUsername(String username) {
        String query = "SELECT * FROM users WHERE username= ?";
        User user = null;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            int id = resultSet.getInt("id");
            String password = resultSet.getString("password");

            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setId(id);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return user;
    }

    public List<User> getUsers() {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setId(id);
                users.add(user);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return users;
    }

}
