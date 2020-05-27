package app.services;

import app.database.Database;
import app.models.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {

    public static List<Ticket> getAllTickets() {
        return Database.getTickets();
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
