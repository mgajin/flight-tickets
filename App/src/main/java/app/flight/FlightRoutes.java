package app.flight;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.delete;

public class FlightRoutes {

    private final FlightController controller;

    public FlightRoutes(FlightController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        get("app/flights", controller.getFlights);
        post("app/flights", controller.createFlight);
        delete("app/flights/:id", controller.deleteFlight);
    }
}
