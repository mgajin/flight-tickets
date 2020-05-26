package app.models;

import java.util.Date;

public class Ticket {

    private Integer id;
    private Company company;
    private boolean oneWay;
    private Date departDate;
    private Date returnDate;
    private Integer flightId;
    private Long count;

    public boolean isOneWay() {
        return oneWay;
    }

}
