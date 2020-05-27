package app.models;

import java.util.List;

public class User {

    private Integer id;
    private String username;
    private String password;
    private UserType type;
    private List<Reservation> bookings;

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
