package app.ticket;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.delete;

public class TicketRoutes {

    static {
        get("/app/tickets", TicketController.getTickets);
        post("/app/tickets", TicketController.createTicket);
        delete("/app/tickets/:id", TicketController.deleteTicket);
    }

}
