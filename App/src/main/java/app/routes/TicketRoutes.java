package app.routes;

import app.controllers.TicketController;

import static spark.Spark.*;

public class TicketRoutes {

    static {
        get("/app/tickets", TicketController.getTickets);
        post("/app/tickets", TicketController.createTicket);
        delete("/app/tickets/:id", TicketController.deleteTicket);
    }

}
