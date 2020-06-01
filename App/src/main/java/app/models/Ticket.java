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

    public Ticket(Integer id) {
        this.id = id;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }

    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public boolean isOneWay() {
        return oneWay;
    }

    public Integer getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public Date getDepartDate() {
        return departDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public Long getCount() {
        return count;
    }
}
