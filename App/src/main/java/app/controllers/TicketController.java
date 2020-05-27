package app.controllers;

import app.models.Ticket;
import app.services.TicketService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class TicketController {

    private static Gson gson = new Gson();

    public static Route getTickets = (Request req, Response res) -> {

        List<Ticket> tickets = TicketService.getAllTickets();

        return gson.toJson(tickets);
    };

    public static Route createTicket = (Request req, Response res) -> {

        String body = req.body();
        Ticket ticket = gson.fromJson(body, Ticket.class);
        TicketService.createTicket(ticket);

        return "";
    };

    public static Route deleteTicket = (Request req, Response res) -> {

        int ticketId = Integer.parseInt(req.params(":id"));
        Ticket ticket = TicketService.getTicket(ticketId);

        TicketService.deleteTicket(ticket);

        return "";
    };

}
