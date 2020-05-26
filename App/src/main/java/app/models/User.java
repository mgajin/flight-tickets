package app.models;

import java.util.List;

public class User {

    private Integer id;
    private String username;
    private String password;
    private UserType type;
    private List<Reservation> bookings;


}
