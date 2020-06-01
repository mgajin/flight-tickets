package app.routes;

import app.controllers.FlightController;

import static spark.Spark.*;

public class FlightRoutes {

    static {
        get("app/flights", FlightController.getFlights);
    }

}
