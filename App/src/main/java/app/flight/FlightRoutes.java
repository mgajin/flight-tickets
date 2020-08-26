package app.flight;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.delete;

public class FlightRoutes {

    static {
        get("app/flights", FlightController.getFlights);
        post("app/flights", FlightController.createFlight);
        delete("app/flights/:id", FlightController.deleteFlight);
    }
}
