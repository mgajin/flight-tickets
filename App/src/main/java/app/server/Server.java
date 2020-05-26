package app.server;

import app.routes.FlightRoutes;
import app.routes.TicketRoutes;

import static spark.Spark.port;

public class Server {

    private static final int PORT = 3000;

    public static void main(String[] args) {

        port(PORT);

        TicketRoutes ticketRoutes = new TicketRoutes();
        FlightRoutes flightRoutes = new FlightRoutes();
    }
}
