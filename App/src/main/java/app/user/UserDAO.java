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

    public boolean insert(User user) {
        String query = "INSERT INTO users (username, password) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    public User getByUsername(String username) {
        User user;
        String query = "SELECT * FROM users WHERE username= ?";
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
            return null;
        }
        return user;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                boolean isAdmin = resultSet.getBoolean("is_admin");
                UserType userType = (isAdmin) ? UserType.ADMIN : UserType.USER;

                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setType(userType);
                user.setId(id);
                users.add(user);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return users;
    }

    public boolean update(User user) {
        boolean isAdmin = user.getType().equals(UserType.ADMIN);
        String query = "UPDATE users set is_admin=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, isAdmin);
            statement.setInt(2, user.getId());
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

}
