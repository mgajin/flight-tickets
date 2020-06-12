package app.ticket;

import app.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {

    private static final Connection connection = Database.getConnection();

    public void insert(Ticket ticket) {
        String query = "INSERT INTO tickets (company, flight, count) values (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, ticket.getCompanyName());
            statement.setInt(2, ticket.getFlightId());
            statement.setInt(3, ticket.getCount());
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    public Ticket getById(int id) {
        Ticket ticket = null;
        String query = "SELECT * FROM tickets WHERE id == (?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next()) {
                ticket = new Ticket();
                int flight = resultSet.getInt("flight");
                String company = resultSet.getString("company");

                ticket.setId(id);
                ticket.setFlightId(flight);
                ticket.setCompanyName(company);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return ticket;
    }

    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            readResultSet(resultSet, tickets);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return tickets;
    }

    public void update() {}

    public void delete() {}

    private void readResultSet(ResultSet resultSet, List<Ticket> tickets) throws SQLException {
        while (resultSet.next()) {
            Ticket ticket = new Ticket();
            int id = resultSet.getInt("id");
            int flight = resultSet.getInt("flight");
            String company = resultSet.getString("company");

            ticket.setId(id);
            ticket.setFlightId(flight);
            ticket.setCompanyName(company);
            tickets.add(ticket);
        }
    }

}
