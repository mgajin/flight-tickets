package app.flight;

import app.city.City;
import app.city.CityService;

import java.util.List;
import java.util.stream.Collectors;

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
        return repository.getFlights();
    }

//    Filter flights by given origin city
    public static List<Flight> getByOrigin(String origin) {
        List<Flight> flights;

        flights = repository.getFlights().stream().filter(flight ->
                flight.getOrigin().getName().equals(origin)
        ).collect(Collectors.toList());

        return flights;
    }

    //    Filter flights by given destination city
    public static List<Flight> getByDestination(String destination) {
        List<Flight> flights;

        flights = repository.getFlights().stream().filter(flight ->
                flight.getDestination().getName().equals(destination)
        ).collect(Collectors.toList());

        return flights;
    }

}
