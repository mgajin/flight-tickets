package app.models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Integer id;
    private String username;
    private String password;
    private UserType type;
    private List<Reservation> bookings;
    private String token;

    public User() {
        bookings = new ArrayList<>();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getType() {
        return type;
    }

    public List<Reservation> getBookings() {
        return bookings;
    }
}
