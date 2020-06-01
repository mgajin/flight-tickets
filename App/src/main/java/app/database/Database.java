package app.database;

import app.models.*;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static String[] CITY_NAMES = {"New York", "Los Angeles", "Boston", "Chicago", "Miami"};
    private static String[] COMPANY_NAMES = {"Air Serbia", "Wizz Air", "Fly"};

    private static List<User> USERS;
    private static List<City> CITIES;
    private static List<Ticket> TICKETS;
    private static List<Flight> FLIGHTS;
    private static List<Company> COMPANIES;
    private static List<Reservation> RESERVATIONS;

    static {
        COMPANIES = generateCompanies();
        CITIES = generateCities();
        USERS = generateUsers();
        TICKETS = generateTickets();
        FLIGHTS = generateFlights();
        RESERVATIONS = new ArrayList<>();
    }

    private static List<User> generateUsers() {

        List<User> list = new ArrayList<>();
        User user = new User();

        user.setId(0);
        user.setUsername("admin");
        user.setPassword("1234");
        user.setType(UserType.ADMIN);

        list.add(user);

        return list;
    }

    private static List<City> generateCities() {

        List<City> list = new ArrayList<>();
        int i = 0;

        for (String city : CITY_NAMES) {
            list.add(new City(i, city));
            i++;
        }

        return list;
    }

    private static List<Flight> generateFlights() {

        List<Flight> list = new ArrayList<>();

        for (int i=0; i < 10; i++) {
            Flight flight = new Flight();

            flight.setOrigin(new City(i,"Belgrade"));
            flight.setDestination(CITIES.get(i%CITIES.toArray().length));
            list.add(flight);
        }

        return list;
    }

    private static List<Ticket> generateTickets() {

        List<Ticket> list = new ArrayList<>();

        for (int i=0; i < 12; i++) {
            Ticket ticket = new Ticket(i);
            ticket.setCompany(COMPANIES.get(i%3));
            ticket.setCount(100L);
            ticket.setOneWay(true);
            ticket.setFlightId(0);

            list.add(ticket);
        }

        return list;
    }

    private static List<Company> generateCompanies() {

        List<Company> list = new ArrayList<>();

        for (int i = 0; i < COMPANY_NAMES.length ; i++) {
            list.add(new Company(i, COMPANY_NAMES[i]));
        }

        return list;
    }

    public synchronized static void addFlight(Flight flight) {
        FLIGHTS.add(flight);
    }

    public synchronized static void addReservation(Reservation reservation) {
        RESERVATIONS.add(reservation);
    }

    public synchronized static void addUser(User user) {
        USERS.add(user);
    }

    public synchronized static void addTicket(Ticket ticket) {
        TICKETS.add(ticket);
    }

    public synchronized static List<User> getUsers() {
        return USERS;
    }

    public synchronized static List<Ticket> getTickets() {
        return TICKETS;
    }

    public synchronized static List<Flight> getFlights() {
        return FLIGHTS;
    }

    public synchronized static List<Company> getCompanies() {
        return COMPANIES;
    }

    public synchronized static List<City> getCities() {
        return CITIES;
    }

    public synchronized static List<Reservation> getReservations() {
        return RESERVATIONS;
    }

}
