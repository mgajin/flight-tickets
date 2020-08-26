package app.flight;

import app.city.City;
import app.city.CityService;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {

    private static final FlightDAO flightsRepo = new FlightDAO();
//    private static final TicketDAO ticketRepo = new FlightDAO();

//    Adds new Flight to database
    public static void createFlight(Flight flight) {
        City origin = CityService.getCity(flight.getOrigin());
        City destination = CityService.getCity(flight.getDestination());

        if (origin != null && destination != null) {
            flightsRepo.createFlight(origin.getName(), destination.getName());
        }
    }

//    Returns all flights in database
    public static List<Flight> getFlights() {
        return flightsRepo.getFlights();
    }

//    Filter flights by given origin city
    public static List<Flight> getByOrigin(String origin) {
        List<Flight> flights;

        flights = flightsRepo.getFlights().stream().filter(flight ->
                flight.getOrigin().getName().equals(origin)
        ).collect(Collectors.toList());

        return flights;
    }

    //    Filter flights by given destination city
    public static List<Flight> getByDestination(String destination) {
        List<Flight> flights;

        flights = flightsRepo.getFlights().stream().filter(flight ->
                flight.getDestination().getName().equals(destination)
        ).collect(Collectors.toList());

        return flights;
    }

    public static List<Flight> deleteFlight(int flightId) {
        flightsRepo.deleteFlightTickets(flightId);
        flightsRepo.deleteFlight(flightId);
        return flightsRepo.getFlights();
    }

}
