package app.ticket;

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

        if (queries.hasKey("oneWay")) {
            boolean isOneWay = Boolean.parseBoolean(queries.get("oneWay").value());
            tickets = (isOneWay) ? TicketService.getOneWayTickets() : TicketService.getTwoWayTickets();
        } else {
            tickets = TicketService.getTickets();
        }

        return gson.toJson(tickets);
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
            response = "";
            status = 401;
        }
        res.status(status);

        return response;
    };

    public static Route createTicket = (Request req, Response res) -> {
        String body = req.body();
        System.out.println("Got: " + body);
        JsonObject json = gson.fromJson(body, JsonObject.class);

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

        List<Ticket> tickets = TicketService.createTicket(ticket);

        res.status(201);
        return gson.toJson(tickets);
    };

    public static Route updateTicket = (Request req, Response res) -> {
        String body = req.body();
        System.out.println("Got: " + body);
        JsonObject json = gson.fromJson(body, JsonObject.class);

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

        List<Ticket> tickets = TicketService.updateTicket(ticket);

        res.status(201);
        return gson.toJson(tickets);
    };

    public static Route deleteTicket = (Request req, Response res) -> {
        int ticketId = Integer.parseInt(req.params(":id"));
        List<Ticket> tickets = TicketService.deleteTicket(ticketId);

        res.status(201);
        return gson.toJson(tickets);
    };
}
