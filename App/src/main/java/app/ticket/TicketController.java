package app.ticket;

import com.google.gson.Gson;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class TicketController {

    private static Gson gson = new Gson();

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
        Ticket ticket = gson.fromJson(body, Ticket.class);
        TicketService.createTicket(ticket);

        return "";
    };

    public static Route deleteTicket = (Request req, Response res) -> {

        int ticketId = Integer.parseInt(req.params(":id"));
        TicketService.deleteTicket(ticketId);

        return "";
    };


}
