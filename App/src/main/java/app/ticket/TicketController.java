package app.ticket;

import app.pagination.PageInfo;
import app.utils.TicketsResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.Date;
import java.util.List;

public class TicketController {

    private static final Gson gson = new Gson();

    public static Route getTickets = (Request req, Response res) -> {

        List<Ticket> tickets;
        QueryParamsMap queries = req.queryMap();
        TicketsResponse ticketsResponse = new TicketsResponse();

        if (queries.hasKey("oneWay")) {
            boolean isOneWay = Boolean.parseBoolean(queries.get("oneWay").value());
            tickets = (isOneWay) ? TicketService.getOneWayTickets() : TicketService.getTwoWayTickets();
        } else {
            tickets = TicketService.getTickets();
        }
        ticketsResponse.setTickets(tickets);

//        Pagination
        if (queries.hasKey("page")) {
            int page = Integer.parseInt(queries.get("page").value());
            PageInfo pageInfo = new PageInfo(page);
            tickets = TicketService.getPaginated(tickets, pageInfo);
            ticketsResponse.setTickets(tickets);
            ticketsResponse.setPageInfo(pageInfo);
        }

        return ticketsResponse.toJson();
    };

    public static Route getTicket = (Request req, Response res) -> {

        int id = Integer.parseInt(req.params("id"));
        String response;
        int status;

        Ticket ticket = TicketService.getTicket(id);

        if (ticket != null) {
            response = gson.toJson(ticket);
            status = 200;
        } else {
            response = "Ticket not found";
            status = 401;
        }
        res.status(status);

        return response;
    };

    public static Route createTicket = (Request req, Response res) -> {
        String body = req.body();
        System.out.println("Got: " + body);
        JsonObject json = gson.fromJson(body, JsonObject.class);
        TicketsResponse ticketsResponse = new TicketsResponse();

        String departDate = (json.get("departDate").getAsString());
        String returnDate = (json.get("returnDate").getAsString());
        String companyName = (json.get("companyName").getAsString());
        boolean oneWay = (json.get("oneWay").getAsBoolean());
        int flightId = (json.get("flightId").getAsInt());
        int count = (json.get("count").getAsInt());

        Ticket ticket = new Ticket();
        ticket.setFlightId(flightId);
        ticket.setOneWay(oneWay);
        ticket.setDepartDate(Date.valueOf(departDate));
        ticket.setReturnDate(Date.valueOf(returnDate));
        ticket.setCompanyName(companyName);
        ticket.setCount(count);

        String response;
        int status = 201;
        if (!TicketService.createTicket(ticket)) {
            response = "Error while creating ticket";
            status = 500;
        } else {
            List<Ticket> tickets = TicketService.getTickets();
            ticketsResponse.setTickets(tickets);
            response = ticketsResponse.toJson();
        }

        res.status(status);
        return response;
    };

    public static Route updateTicket = (Request req, Response res) -> {
        String body = req.body();
        System.out.println("Got: " + body);
        JsonObject json = gson.fromJson(body, JsonObject.class);
        TicketsResponse ticketsResponse = new TicketsResponse();

        String departDate = (json.get("departDate").getAsString());
        String returnDate = (json.get("returnDate").getAsString());
        String companyName = (json.get("companyName").getAsString());
        boolean oneWay = (json.get("oneWay").getAsBoolean());
        int id = (json.get("id").getAsInt());
        int flightId = (json.get("flightId").getAsInt());
        int count = (json.get("count").getAsInt());

        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setFlightId(flightId);
        ticket.setOneWay(oneWay);
        ticket.setDepartDate(Date.valueOf(departDate));
        ticket.setReturnDate(Date.valueOf(returnDate));
        ticket.setCompanyName(companyName);
        ticket.setCount(count);

        String response;
        int status = 200;

        if (TicketService.updateTicket(ticket)) {
            response = "Error while updating ticket";
            status = 500;
        } else {
            List<Ticket> tickets = TicketService.getTickets();
            ticketsResponse.setTickets(tickets);
            response = ticketsResponse.toJson();
        }

        res.status(status);
        return response;
    };

    public static Route deleteTicket = (Request req, Response res) -> {
        int ticketId = Integer.parseInt(req.params(":id"));
        String response;
        int status = 200;
        TicketsResponse ticketsResponse = new TicketsResponse();

        if (TicketService.deleteTicket(ticketId)) {
            response = "Error while deleting ticket";
            status = 500;
        } else {
            List<Ticket> tickets = TicketService.getTickets();
            ticketsResponse.setTickets(tickets);
            response = ticketsResponse.toJson();
        }
        
        res.status(status);
        return response;
    };
}
