package app.flight;

import static spark.Spark.get;
import static spark.Spark.post;

public class FlightRoutes {

    static {
        get("app/flights", FlightController.getFlights);
        post("app/flights", FlightController.createFlight);
    }
}
