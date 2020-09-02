package app.flight;

import app.city.City;
import app.database.Dao;
import app.ticket.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {

    private final Dao<Flight> flightsRepo;
    private final Dao<City> citiesRepo;
    private final Dao<Ticket> ticketsRepo;

    public FlightService(Dao<Flight> flightsRepo, Dao<City> citiesRepo, Dao<Ticket> ticketsRepo) {
        this.flightsRepo = flightsRepo;
        this.citiesRepo = citiesRepo;
        this.ticketsRepo = ticketsRepo;
    }

    public boolean createFlight(Flight flight) {
        City origin = citiesRepo.find(flight.getOrigin().getName());
        City destination = citiesRepo.find(flight.getDestination().getName());
        if (origin != null && destination != null) {
            return flightsRepo.insert(flight);
        }
        return false;
    }

    public List<Flight> getFlights() {
        return flightsRepo.getAll();
    }

    public List<Flight> getByOrigin(String origin) {
        List<Flight> flights;
        flights = flightsRepo.getAll().stream().filter(flight ->
                flight.getOrigin().getName().equals(origin)
        ).collect(Collectors.toList());

        return flights;
    }

    public List<Flight> getByDestination(String destination) {
        List<Flight> flights;

        flights = flightsRepo.getAll()
            .stream()
            .filter(flight ->
                flight.getDestination().getName().equals(destination)
            )
            .collect(Collectors.toList());

        return flights;
    }

    public boolean deleteFlight(int flightId) {
        String query = "DELETE FROM tickets WHERE flight = (?)";
        if (ticketsRepo.delete(query, flightId)) {
            query = "DELETE FROM flights WHERE id = (?)";
            return flightsRepo.delete(query, flightId);
        }
        return false;
    }
}
