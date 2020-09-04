package app.server;

import app.authentication.AuthController;
import app.authentication.AuthRoutes;
import app.authentication.AuthService;
import app.city.*;
import app.company.*;
import app.database.Dao;
import app.database.Database;
import app.flight.*;
import app.reservation.*;
import app.ticket.*;
import app.user.*;

import java.util.Properties;

import static spark.Spark.*;

public class Server {

    private static final int PORT = 3000;
    private static final String database = "jdbc:postgresql://localhost:5432/flight_tickets";
    private static final String username = "postgres";
    private static final String password = "postgres";

    public static void main(String[] args) {
        port(PORT);
        initDatabase();
        enableCORS();
        initModules();
    }

    private static void initModules() {
        Dao<Reservation> reservationDao = new ReservationDao();
        Dao<Ticket> ticketDao = new TicketDao();
        Dao<Flight> flightDao = new FlightDao();
        Dao<User> userDao = new UserDao();
        Dao<City> cityDao = new CityDao();
        Dao<Company> companyDao = new CompanyDao();

        TicketService ticketService = new TicketService(ticketDao);
        UserService userService = new UserService(userDao);
        AuthService authService = new AuthService();
        FlightService flightService = new FlightService(flightDao, cityDao, ticketDao);
        ReservationService reservationService = new ReservationService(reservationDao);
        CityService cityService = new CityService(cityDao);
        CompanyService companyService = new CompanyService(companyDao, ticketDao);

        TicketController ticketsController = new TicketController(ticketService);
        UserController userController = new UserController(userService);
        AuthController authController = new AuthController(userService, authService);
        FlightController flightController = new FlightController(flightService);
        ReservationController reservationController = new ReservationController(reservationService);
        CityController cityController = new CityController(cityService);
        CompanyController companyController = new CompanyController(companyService, ticketService);

        TicketRoutes ticketRoutes = new TicketRoutes(ticketsController);
        UserRoutes userRoutes = new UserRoutes(userController);
        AuthRoutes authRoutes = new AuthRoutes(authController);
        FlightRoutes flightRoutes = new FlightRoutes(flightController);
        ReservationRoutes reservationRoutes = new ReservationRoutes(reservationController);
        CityRoutes cityRoutes = new CityRoutes(cityController);
        CompanyRoutes companyRoutes = new CompanyRoutes(companyController);
    }

    private static void initDatabase() {
        Properties props = new Properties();
        props.put("url", database);
        props.put("user", username);
        props.put("password", password);

        Database.init(props);
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
