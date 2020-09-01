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

    private static final Gson gson = new Gson();

    public static Route getFlights = (Request req, Response res) -> {

        List<Flight> flights;
        String response;
        QueryParamsMap queries = req.queryMap();

        if (queries.hasKey("origin")) {
            String origin = queries.get("origin").value();
            flights = FlightService.getByOrigin(origin);
        } else if (queries.hasKey("destination")){
            String destination = queries.get("destination").value();
            flights = FlightService.getByDestination(destination);
        } else {
            flights = FlightService.getFlights();
        }

        if (flights == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Flights not found");
            res.status(404);

            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setFlights(flights);

        res.type("application/json");
        res.status(200);

        return successResponse.toJson();
    };

    public static Route createFlight = (Request req, Response res) -> {

        String response;
        String body = req.body();
        JsonObject json = gson.fromJson(body, JsonObject.class);

        City origin = new City(json.get("origin").getAsString());
        City destination = new City(json.get("destination").getAsString());

        Flight flight = new Flight();
        flight.setOrigin(origin);
        flight.setDestination(destination);

        FlightService.createFlight(flight);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setFlights(FlightService.getFlights());

        res.type("application/json");
        res.status(201);

        return successResponse.toJson();
    };

    public static Route deleteFlight = (Request req, Response res) -> {
        int flightId = Integer.parseInt(req.params(":id"));
        List<Flight> flights = FlightService.deleteFlight(flightId);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setFlights(FlightService.getFlights());

        res.status(201);
        return successResponse.toJson();
    };
}