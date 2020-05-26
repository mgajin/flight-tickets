package app.database;

import app.models.Company;
import app.models.Ticket;
import app.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Database {

    private static String[] CITIES = {};
    private static String[] COMPANY_NAMES = {"Air Serbia", "Wizz Air", "Fly"};

    private static List<User> USERS;
    private static List<Ticket> TICKETS;
    private static List<Company> COMPANIES;

    static {
        USERS = new ArrayList<>();
        TICKETS = generateTickets();
        COMPANIES = generateCompanies();
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

    public synchronized static List<User> getUsers() {
        return USERS;
    }

    public synchronized static void addTicket(Ticket ticket) {
        TICKETS.add(ticket);
    }

    public synchronized static List<Ticket> getAllTickets() {
        return TICKETS;
    }

    public synchronized static List<Ticket> getOneWayTickets() {
        return TICKETS.stream().filter(Ticket::isOneWay).collect(Collectors.toList());
    }

    public synchronized static List<Ticket> getTwoWayTickets() {
        return TICKETS.stream().filter(ticket -> !ticket.isOneWay()).collect(Collectors.toList());
    }

    public synchronized static List<Company> getCompanies() {
        return COMPANIES;
    }

}
