package app.controllers;

import app.database.Database;
import app.models.Flight;
import app.services.FlightService;
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

        String filter;
        QueryParamsMap queries = req.queryMap();

        if (queries.hasKey("origin")) {
            filter = queries.get("origin").value();
            flights = FlightService.getByOrigin(filter);
        } else if (queries.hasKey("destination")) {
            filter = queries.get("destination").value();
            flights = FlightService.getByDestination(filter);
        } else {
            flights = Database.getFlights();
        }

        return gson.toJson(flights);
    };

//    public static Route getFlight = (Request req, Response res) -> {
//
//        Flight flight = FlightService.
//
//        return ;
//    }

}
