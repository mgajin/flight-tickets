package app.reservation;

import app.database.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao extends Dao<Reservation> {

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            readResultSet(resultSet, reservations);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
        return reservations;
    }

    @Override
    public Reservation getById(int id) {
        return null;
    }

    @Override
    public Reservation find(Object item) {
        return null;
    }

    @Override
    public boolean insert(Reservation reservation) {
        String query = "INSERT INTO reservations (user_id, ticket, flight) values (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reservation.getUser());
            statement.setInt(2, reservation.getTicket());
            statement.setInt(3, reservation.getFlight());
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Reservation item) {
        return true;
    }

    @Override
    public boolean delete(String query, int id) {
        return false;
    }

    @Override
    protected void readResultSet(ResultSet resultSet, List<Reservation> reservations) throws SQLException {
        while (resultSet.next()) {
            Reservation reservation = getResultData(resultSet);
            reservations.add(reservation);
        }
    }

    @Override
    protected Reservation getResultData(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        int id = resultSet.getInt("id");
        int user = resultSet.getInt("user_id");
        int ticket = resultSet.getInt("ticket");
        int flight = resultSet.getInt("flight");

        reservation.setId(id);
        reservation.setUser(user);
        reservation.setTicket(ticket);
        reservation.setFlight(flight);

        return reservation;
    }
}
