package app.ticket;

import app.database.Dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDao extends Dao<Ticket> {

    @Override
    public boolean insert(Ticket ticket) {
        String query = "INSERT INTO tickets (company, flight, depart_date, return_date, one_way, count) values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            setTicketData(ticket, statement);
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Ticket getById(int id) {
        Ticket ticket = null;
        String query = "SELECT * FROM tickets WHERE id = (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                ticket = getResultData(resultSet);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return ticket;
    }

    @Override
    public Ticket find(Object item) {
        return null;
    }

    @Override
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
            return null;
        }
        return tickets;
    }

    @Override
    public boolean update(Ticket ticket) {
        String query = "UPDATE tickets set company=?, flight=?, depart_date=?, return_date=?, one_way=?, count=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            setTicketData(ticket, statement);
            statement.setInt(7, ticket.getId());
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String query, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            return true;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    @Override
    protected void readResultSet(ResultSet resultSet, List<Ticket> tickets) throws SQLException {
        while (resultSet.next()) {
            Ticket ticket = getResultData(resultSet);
            tickets.add(ticket);
        }
    }

    @Override
    protected Ticket getResultData(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();

        int id = resultSet.getInt("id");
        int flight = resultSet.getInt("flight");
        String company = resultSet.getString("company");
        Date departDate = resultSet.getDate("depart_date");
        Date returnDate = resultSet.getDate("return_date");
        boolean oneWay = resultSet.getBoolean("one_way");

        ticket.setId(id);
        ticket.setFlightId(flight);
        ticket.setCompanyName(company);
        ticket.setDepartDate(departDate);
        ticket.setReturnDate(returnDate);
        ticket.setOneWay(oneWay);

        return ticket;
    }

    private void setTicketData(Ticket ticket, PreparedStatement statement) throws SQLException {
        statement.setString(1, ticket.getCompanyName());
        statement.setInt(2, ticket.getFlightId());
        statement.setDate(3, ticket.getDepartDate());
        statement.setDate(4, ticket.getReturnDate());
        statement.setBoolean(5, ticket.isOneWay());
        statement.setInt(6, ticket.getCount());
    }
}
