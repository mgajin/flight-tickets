package app.flight;

import app.city.City;
import app.city.CityService;

import java.util.ArrayList;
import java.util.List;

public class FlightService {

    private static final FlightDAO repository = new FlightDAO();

//    Adds new Flight to database
    public static void createFlight(Flight flight) {
        City origin = CityService.getCity(flight.getOrigin());
        City destination = CityService.getCity(flight.getDestination());

        if (origin != null && destination != null) {
            repository.createFlight(origin.getName(), destination.getName());
        }
    }

//    Returns all flights in database
    public static List<Flight> getFlights() {
        // TODO implement DAO
        return new ArrayList<>();
    }

//    Returns flights with given origin city
    public static List<Flight> getByOrigin(String origin) {
        // TODO implement DAO
        return new ArrayList<>();
    }

    //    Returns flights with given destination city
    public static List<Flight> getByDestination(String destination) {
        // TODO implement DAO
        return new ArrayList<>();
    }

}
