package app.ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketService {

    private static final TicketDao repository = new TicketDao();

    public static List<Ticket> getTickets() {
        return repository.getAll();
    }

    public static List<Ticket> getOneWayTickets() {

        return new ArrayList<>();
    }

    public static List<Ticket> getTwoWayTickets() {


        return new ArrayList<>();
    }

    public static void createTicket(Ticket ticket) {
        repository.insert(ticket);
    }

    public static Ticket getTicket(int ticketId) {
        return repository.getById(ticketId);
    }

    public static void deleteTicket(int ticketId) {}
}
