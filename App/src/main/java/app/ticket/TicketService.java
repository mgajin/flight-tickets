package app.ticket;

import app.pagination.PageInfo;
import app.pagination.PaginationResponse;

import java.util.ArrayList;
import java.util.List;

public class TicketService {

    private static final TicketDao repository = new TicketDao();

    public static List<Ticket> getPaginated(List<Ticket> tickets, PageInfo pageInfo) {
        int totalPages = (tickets.size() + pageInfo.getLimit() - 1) / pageInfo.getLimit();
        pageInfo.setTotalPages(totalPages);

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setModel(tickets);
        paginationResponse.setPageInfo(pageInfo);

        return paginationResponse.getModel();
    }

    public static List<Ticket> getTickets() {
        return repository.getAll();
    }

    public static List<Ticket> getOneWayTickets() {
        return new ArrayList<>();
    }

    public static List<Ticket> getTwoWayTickets() {
        return new ArrayList<>();
    }

    public static boolean createTicket(Ticket ticket) {
        return repository.insert(ticket);
    }

    public static boolean updateTicket(Ticket ticket) {
        return repository.update(ticket);
    }

    public static Ticket getTicket(int ticketId) {
        return repository.getById(ticketId);
    }

    public static boolean deleteTicket(int ticketId) {
        return repository.delete(ticketId);
    }
}