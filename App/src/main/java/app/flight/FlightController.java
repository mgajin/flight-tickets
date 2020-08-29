package app.flight;

import app.city.City;
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

        response = gson.toJson(flights);
        res.type("application/json");
        res.status(200);

        return response;
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

        response = gson.toJson(flight);
        res.type("application/json");
        res.status(201);

        return response;
    };

    public static Route deleteFlight = (Request req, Response res) -> {
        int flightId = Integer.parseInt(req.params(":id"));
        List<Flight> flights = FlightService.deleteFlight(flightId);

        res.status(201);
        return gson.toJson(flights);
    };
}