package app.server;

import app.controllers.AuthController;
import app.routes.AuthRoutes;
import app.routes.FlightRoutes;
import app.routes.TicketRoutes;

import static spark.Spark.*;

public class Server {

    private static final int PORT = 3000;

    public static void main(String[] args) {

        port(PORT);

        enableCORS();

        TicketRoutes ticketRoutes = new TicketRoutes();
        FlightRoutes flightRoutes = new FlightRoutes();
//        AuthRoutes authRoutes = new AuthRoutes();

        get("/app/auth/login", AuthController.login);

    }

    private static void enableCORS() {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            response.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }
}
