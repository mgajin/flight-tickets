package app.flight;

import app.city.City;
import app.city.CityService;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {

    private final FlightDAO flightsRepo = new FlightDAO();

    public boolean createFlight(Flight flight) {
        City origin = CityService.getCity(flight.getOrigin());
        City destination = CityService.getCity(flight.getDestination());
        if (origin != null && destination != null) {
            return flightsRepo.createFlight(origin.getName(), destination.getName());
        }
        return false;
    }

    public List<Flight> getFlights() {
        return flightsRepo.getFlights();
    }

    public List<Flight> getByOrigin(String origin) {
        List<Flight> flights;
        flights = flightsRepo.getFlights().stream().filter(flight ->
                flight.getOrigin().getName().equals(origin)
        ).collect(Collectors.toList());

        return flights;
    }

    public List<Flight> getByDestination(String destination) {
        List<Flight> flights;

        flights = flightsRepo.getFlights()
            .stream()
            .filter(flight ->
                flight.getDestination().getName().equals(destination)
            )
            .collect(Collectors.toList());

        return flights;
    }

    public boolean deleteFlight(int flightId) {
        if (flightsRepo.deleteFlightTickets(flightId)) {
            return flightsRepo.deleteFlight(flightId);
        }
        return false;
    }
}
