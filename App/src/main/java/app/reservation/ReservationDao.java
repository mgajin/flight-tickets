package app.reservation;

import app.city.City;
import app.company.Company;
import app.database.Dao;
import app.flight.Flight;
import app.ticket.Ticket;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao extends Dao<Reservation> {

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations " +
                "INNER JOIN tickets ON reservations.ticket = tickets.id " +
                "INNER JOIN flights ON tickets.flight = flights.id " +
                "INNER JOIN companies ON tickets.company = companies.id";
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
            statement.setInt(2, reservation.getTicket().getId());
            statement.setInt(3, reservation.getFlight().getId());
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
        int ticketId = resultSet.getInt("ticket");
        int flightId = resultSet.getInt("flight");
        int companyId = resultSet.getInt("company");
        String companyName = resultSet.getString("name");
        Date departDate = resultSet.getDate("depart_date");
        Date returnDate = resultSet.getDate("return_date");
        boolean oneWay = resultSet.getBoolean("one_way");
        String origin = resultSet.getString("origin");
        String destination = resultSet.getString("destination");

        Company company = new Company();
        company.setId(companyId);
        company.setName(companyName);
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        ticket.setCompany(company);
        ticket.setDepartDate(departDate);
        ticket.setReturnDate(returnDate);
        ticket.setOneWay(oneWay);

        Flight flight = new Flight();
        flight.setId(flightId);
        flight.setOrigin(new City(origin));
        flight.setDestination(new City(destination));

        reservation.setId(id);
        reservation.setUser(user);
        reservation.setTicket(ticket);
        reservation.setFlight(flight);

        return reservation;
    }
}
