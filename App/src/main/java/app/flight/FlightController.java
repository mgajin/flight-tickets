package app.flight;

import app.city.City;
import app.utils.ErrorResponse;
import app.utils.SuccessResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class FlightController {

    private FlightService flightService;

    private final Gson gson = new Gson();

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public Route getFlights = (Request req, Response res) -> {
        List<Flight> flights;
        QueryParamsMap queries = req.queryMap();

        if (queries.hasKey("origin")) {
            String origin = queries.get("origin").value();
            flights = flightService.getByOrigin(origin);
        } else if (queries.hasKey("destination")){
            String destination = queries.get("destination").value();
            flights = flightService.getByDestination(destination);
        } else {
            flights = flightService.getFlights();
        }

        if (flights == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Flights not found");
            res.status(404);

            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setFlights(flights);

        res.status(200);
        res.type("application/json");
        return successResponse.toJson();
    };

    public Route createFlight = (Request req, Response res) -> {
        String body = req.body();
        JsonObject json = gson.fromJson(body, JsonObject.class);

        City origin = new City(json.get("origin").getAsString());
        City destination = new City(json.get("destination").getAsString());

        Flight flight = new Flight();
        flight.setOrigin(origin);
        flight.setDestination(destination);

        if (!flightService.createFlight(flight)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while deleting flight");
            res.status(500);

            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setFlights(flightService.getFlights());

        res.status(201);
        res.type("application/json");
        return successResponse.toJson();
    };

    public Route deleteFlight = (Request req, Response res) -> {
        int flightId = Integer.parseInt(req.params(":id"));

        if (!flightService.deleteFlight(flightId)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while deleting flight");
            res.status(500);

            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setFlights(flightService.getFlights());

        res.status(200);
        res.type("application/json");
        return successResponse.toJson();
    };
}