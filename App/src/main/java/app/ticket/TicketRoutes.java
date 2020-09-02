package app.ticket;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.delete;
import static spark.Spark.put;

public class TicketRoutes {

    private final TicketController controller;

    public TicketRoutes(TicketController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        get("/app/tickets", controller.getTickets);
        get("/app/tickets/:id", controller.getTicket);
        post("/app/tickets", controller.createTicket);
        delete("/app/tickets/:id", controller.deleteTicket);
        put("/app/tickets/:id", controller.updateTicket);
    }
}
