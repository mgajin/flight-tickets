package app.ticket;

import app.database.Dao;
import app.pagination.PageInfo;
import app.pagination.PaginationResponse;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {

    private final Dao<Ticket> repository;

    public TicketService(Dao<Ticket> repository) {
        this.repository = repository;
    }

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
        return repository
            .getAll()
            .stream()
            .filter(Ticket::isOneWay)
            .collect(Collectors.toList());
    }

    public List<Ticket> getTwoWayTickets() {
        return repository
            .getAll()
            .stream()
            .filter(ticket -> !ticket.isOneWay())
            .collect(Collectors.toList());
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
        String query = "DELETE FROM tickets WHERE id = (?)";
        return repository.delete(query, ticketId);
    }
}