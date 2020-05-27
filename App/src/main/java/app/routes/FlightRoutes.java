package app.routes;

import app.controllers.FlightController;

import static spark.Spark.*;

public class FlightRoutes {

    static {
        get("app/routes", FlightController.getFlights);
    }

}
