package app.reservation;

import app.flight.Flight;
import app.ticket.Ticket;
import app.user.User;

public class Reservation {

    private int id;
    private boolean isAvailable;
    private Flight flight;
    private Ticket ticket;
    private User user;
}
