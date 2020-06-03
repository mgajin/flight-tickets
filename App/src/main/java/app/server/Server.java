package app.server;

import app.authentication.AuthRoutes;
import app.database.Database;
import app.flight.FlightRoutes;
import app.ticket.TicketRoutes;
import app.user.UserRoutes;

import java.util.Properties;

import static spark.Spark.*;

public class Server {

    private static final int PORT = 3000;
    private static final String database = "jdbc:postgresql://localhost:5432/online_tickets";
    private static final String username = "postgres";
    private static final String password = "tringusija4";

    public static void main(String[] args) {

        port(PORT);
        initDatabase();
        enableCORS();
        initRoutes();
    }

    private static void initDatabase() {
        Properties props = new Properties();
        props.put("url", database);
        props.put("user", username);
        props.put("password", password);

        Database.init(props);
    }

    private static void initRoutes() {
        TicketRoutes ticketRoutes = new TicketRoutes();
        FlightRoutes flightRoutes = new FlightRoutes();
        AuthRoutes authRoutes = new AuthRoutes();
        UserRoutes userRoutes = new UserRoutes();
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
