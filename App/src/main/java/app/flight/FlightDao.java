package app.flight;

import app.city.City;
import app.database.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDao extends Dao<Flight> {

    @Override
    public boolean insert(Flight flight) {
        String query = "INSERT INTO flights (origin, destination) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, flight.getOrigin().getName());
            statement.setString(2, flight.getDestination().getName());
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Flight> getAll() {
        List<Flight> flights = new ArrayList<>();
        String query = "SELECT * FROM flights";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            readResultSet(resultSet, flights);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
        return flights;
    }

    @Override
    public Flight getById(int id) {
        return null;
    }

    @Override
    public Flight find(Object item) {
        return null;
    }

    @Override
    public boolean update(Flight item) {
        return false;
    }

    @Override
    public boolean delete(String query, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    protected void readResultSet(ResultSet resultSet, List<Flight> flights) throws SQLException {
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

    @Override
    protected Flight getResultData(ResultSet resultSet) throws SQLException {
        return null;
    }
}
