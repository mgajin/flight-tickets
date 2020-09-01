package app.utils;

import app.city.City;
import app.company.Company;
import app.flight.Flight;
import app.pagination.PageInfo;
import app.ticket.Ticket;
import app.user.User;
import com.google.gson.Gson;

import java.util.List;

public class SuccessResponse {

    private static final Gson gson = new Gson();

    private List<Ticket> tickets;
    private List<Flight> flights;
    private List<User> users;
    private List<Company> companies;
    private List<City> cities;

    private PageInfo pageInfo;
    private String token;
    private User user;
    private boolean success;

    public SuccessResponse() {
        tickets = null;
        flights = null;
        users = null;
        companies = null;
        cities = null;
        pageInfo = null;
        token = null;
        user = null;
        success = true;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String toJson() {
        return gson.toJson(this);
    }
}
