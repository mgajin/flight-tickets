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

        List<app.models.Ticket> tickets;
        QueryParamsMap queries = req.queryMap();

        if (queries.hasKey("oneWay")) {
            boolean isOneWay = Boolean.parseBoolean(queries.get("oneWay").value());
            tickets = (isOneWay) ? TicketService.getOneWayTickets() : TicketService.getTwoWayTickets();
        } else {
            tickets = TicketService.getTickets();
        }

        return gson.toJson(tickets);
    };

    public static Route createTicket = (Request req, Response res) -> {

        String body = req.body();
        app.models.Ticket ticket = gson.fromJson(body, app.models.Ticket.class);
        TicketService.createTicket(ticket);

        return "";
    };

    public static Route deleteTicket = (Request req, Response res) -> {

        int ticketId = Integer.parseInt(req.params(":id"));
        TicketService.deleteTicket(ticketId);

        return "";
    };


}
