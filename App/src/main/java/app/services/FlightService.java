package app.services;

import app.database.Database;
import app.models.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {

    //    Get flights from database filtered by origin city
    public static List<Flight> getByOrigin(String city) {
        List<Flight> flights = Database.getFlights();

        return flights.stream().filter(flight -> flight.origin.name.equals(city)).collect(Collectors.toList());
    }

    //    Get flights from database filtered by destination city
    public static List<Flight> getByDestination(String city) {
        List<Flight> flights = Database.getFlights();
        return flights.stream().filter(flight -> flight.destination.name.equals(city)).collect(Collectors.toList());
    }

}
