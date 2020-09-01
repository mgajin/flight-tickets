package app.utils;

import app.pagination.PageInfo;
import app.ticket.Ticket;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TicketsResponse {

    private static final Gson gson = new Gson();

    private List<Ticket> tickets;
    private PageInfo pageInfo;

    public TicketsResponse() {
        this.tickets = new ArrayList<>();
        this.pageInfo = null;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String toJson() {
        return gson.toJson(this);
    }
}
