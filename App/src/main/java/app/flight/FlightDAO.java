package app.flight;

import app.city.City;
import app.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Flight> getFlights() {
        List<Flight> flights = new ArrayList<>();
        String query = "SELECT * FROM flights";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            readResultSet(resultSet, flights);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return flights;
    }

    public void deleteFlightTickets(int id) {
        String query = "DELETE FROM tickets WHERE flight = (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteFlight(int id) {
        String query = "DELETE FROM flights WHERE id = (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private void readResultSet(ResultSet resultSet, List<Flight> flights) throws SQLException {
        while (resultSet.next()) {
            Flight flight = new Flight();
            int id = resultSet.getInt("id");
            String origin = resultSet.getString("origin");
            String destination = resultSet.getString("destination");

            flight.setId(id);
            flight.setOrigin(new City(origin));
            flight.setDestination(new City(destination));
            flights.add(flight);
        }
    }
}
