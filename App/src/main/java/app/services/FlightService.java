package app.services;

import app.database.Database;
import app.models.Flight;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {

    public static Route getFlights = (Request req, Response res) -> {

        List<Flight> flights;

        String filter;
        QueryParamsMap queries = req.queryMap();

        if (queries.hasKey("origin")) {
            filter = queries.get("origin").value();
            flights = getByOrigin(filter);
        } else if (queries.hasKey("destination")) {
            filter = queries.get("destination").value();
            flights = getByDestination(filter);
        } else {
            flights = Database.getFlights();
        }

        return flights;
    };

//    Get flights from database filtered by origin city
    private static List<Flight> getByOrigin(String city) {
        List<Flight> flights = Database.getFlights();

        return flights.stream().filter(flight -> flight.origin.name.equals(city)).collect(Collectors.toList());
    }

    //    Get flights from database filtered by destination city
    private static List<Flight> getByDestination(String city) {
        List<Flight> flights = Database.getFlights();
        return flights.stream().filter(flight -> flight.destination.name.equals(city)).collect(Collectors.toList());
    }
}
