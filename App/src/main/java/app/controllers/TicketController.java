package app.controllers;

import app.services.TicketService;
import spark.Request;
import spark.Response;
import spark.Route;

public class TicketController {

    public static Route getTickets = (Request req, Response res) -> {

        return TicketService.getAllTickets();
    };

}
