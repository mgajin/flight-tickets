package app.routes;

import app.services.FlightService;

import static spark.Spark.*;

public class FlightRoutes {

    static {
        get("app/routes", FlightService.getFlights);
    }

}
