package app.user;

import app.database.Dao;
import app.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao<User> {

    private final Connection connection = Database.getConnection();

    @Override
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

    @Override
    public User find(Object username) {
        User user = null;
        String query = "SELECT * FROM users WHERE username= ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, (String) username);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                user = getResultData(resultSet);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            readResultSet(resultSet, users);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
        return users;
    }

    @Override
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

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public boolean delete(String query, int id) {
        return false;
    }

    @Override
    protected void readResultSet(ResultSet resultSet, List<User> users) throws SQLException {
        while (resultSet.next()) {
            User user = getResultData(resultSet);
            users.add(user);
        }
    }

    @Override
    protected User getResultData(ResultSet resultSet) throws SQLException {
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

        return user;
    }
}
