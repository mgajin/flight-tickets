package app.routes;

import app.services.TicketService;

import static spark.Spark.*;

public class TicketRoutes {

    static {
        get("/app/tickets", TicketService.getTickets);
    }



}
