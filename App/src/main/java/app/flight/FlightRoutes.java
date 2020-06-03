package app.flight;

import static spark.Spark.get;

public class FlightRoutes {

    static {
        get("app/flights", FlightController.getFlights);
    }
}
