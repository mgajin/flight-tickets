package app.services;

import app.database.Database;
import app.database.Postgres;
import app.database.Queries;
import app.models.Company;
import app.models.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TicketService {

    public static void createTicket(Ticket ticket) {
        Database.addTicket(ticket);
    }

    public static Ticket getTicket(int id) {

        for (Ticket ticket : Database.getTickets()) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }

        return null;
    }

    public static void deleteTicket(Ticket ticket) {
        Database.getTickets().remove(ticket);
    }

    public static List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();

        try {
            ResultSet results = Queries.getTickets();
//            while (results.next()) {
//                int id = results.getInt("id");
//                boolean oneWay = results.getBoolean("oneWay");
//                Date departDate = results.getDate("departureDate");
//                Date returnDate = results.getDate("returnDate");
//                int flightId = results.getInt("flight");
//                Long count = results.getLong("count");
//                Company company results.ged;
//                tickets.add(new Ticket());
//            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

//        return Database.getTickets();
        return tickets;
    }

    public static List<Ticket> getOneWayTickets() {
        List<Ticket> tickets = Database.getTickets();

        return tickets.stream().filter(Ticket::isOneWay).collect(Collectors.toList());
    }

    public static List<Ticket> getTwoWayTickets() {
        List<Ticket> tickets = Database.getTickets();

        return tickets.stream().filter(ticket -> !ticket.isOneWay()).collect(Collectors.toList());
    }

}
