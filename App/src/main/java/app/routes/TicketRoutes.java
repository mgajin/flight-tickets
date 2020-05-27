package app.routes;

import app.controllers.TicketController;

import static spark.Spark.*;

public class TicketRoutes {

    static {
        get("/app/tickets", TicketController.getTickets);
    }

}
