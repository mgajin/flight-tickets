package app.flight;

import com.google.gson.Gson;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class FlightController {

    private static Gson gson = new Gson();

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

        return response;
    };
}
