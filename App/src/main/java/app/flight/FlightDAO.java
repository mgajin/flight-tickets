package app.flight;

import app.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FlightDAO {

    private static final Connection connection = Database.getConnection();

    public void createFlight(String origin, String destination) {
        String query = "INSERT INTO flights (origin, destination) values (?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, origin);
            statement.setString(2, destination);
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
