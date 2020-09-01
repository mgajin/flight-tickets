package app.ticket;

import app.pagination.PageInfo;
import app.pagination.PaginationResponse;

import java.util.ArrayList;
import java.util.List;

public class TicketService {

    private final TicketDao repository = new TicketDao();

    public List<Ticket> getPaginated(List<Ticket> tickets, PageInfo pageInfo) {
        int totalPages = (tickets.size() + pageInfo.getLimit() - 1) / pageInfo.getLimit();
        pageInfo.setTotalPages(totalPages);

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setModel(tickets);
        paginationResponse.setPageInfo(pageInfo);

        return paginationResponse.getModel();
    }

    public List<Ticket> getTickets() {
        return repository.getAll();
    }

    public List<Ticket> getOneWayTickets() {
        return new ArrayList<>();
    }

    public List<Ticket> getTwoWayTickets() {
        return new ArrayList<>();
    }

    public boolean createTicket(Ticket ticket) {
        return repository.insert(ticket);
    }

    public boolean updateTicket(Ticket ticket) {
        return repository.update(ticket);
    }

    public Ticket getTicket(int ticketId) {
        return repository.getById(ticketId);
    }

    public boolean deleteTicket(int ticketId) {
        return repository.delete(ticketId);
    }
}