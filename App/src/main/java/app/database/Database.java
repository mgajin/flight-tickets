package app.database;

import app.models.Company;
import app.models.Flight;
import app.models.Ticket;
import app.models.User;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static String[] CITIES = {};
    private static String[] COMPANY_NAMES = {"Air Serbia", "Wizz Air", "Fly"};

    private static List<User> USERS;
    private static List<Ticket> TICKETS;
    private static List<Company> COMPANIES;
    private static List<Flight> FLIGHTS;

    static {
        USERS = new ArrayList<>();
        FLIGHTS = generateFlights();
        TICKETS = generateTickets();
        COMPANIES = generateCompanies();
    }

    private static List<Flight> generateFlights() {

        return new ArrayList<>();
    }

    private static List<Ticket> generateTickets() {

        return new ArrayList<>();
    }

    private static List<Company> generateCompanies() {

        List<Company> list = new ArrayList<>();

        for (int i = 0; i < COMPANY_NAMES.length ; i++) {
            list.add(new Company(i, COMPANY_NAMES[i]));
        }

        return list;
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

}
