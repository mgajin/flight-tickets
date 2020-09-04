package app.ticket;

import app.company.Company;
import app.flight.Flight;
import app.pagination.PageInfo;
import app.utils.ErrorResponse;
import app.utils.SuccessResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.Date;
import java.util.List;

public class TicketController {

    private static final Gson gson = new Gson();
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public Route getTickets = (Request req, Response res) -> {
        QueryParamsMap queries = req.queryMap();
        List<Ticket> tickets;

        if (queries.hasKey("oneWay")) {
            boolean isOneWay = Boolean.parseBoolean(queries.get("oneWay").value());
            tickets = (isOneWay) ? ticketService.getOneWayTickets() : ticketService.getTwoWayTickets();
        } else {
            tickets = ticketService.getTickets();
        }

        if (tickets == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Tickets not found");
            res.status(404);

            return errorResponse.toJson();
        }
//        Pagination
        SuccessResponse successResponse = new SuccessResponse();
        if (queries.hasKey("page")) {
            int page = Integer.parseInt(queries.get("page").value());
            PageInfo pageInfo = new PageInfo(page);
            tickets = ticketService.getPaginated(tickets, pageInfo);
            successResponse.setPageInfo(pageInfo);
        }
        successResponse.setTickets(tickets);

        res.status(200);
        return successResponse.toJson();
    };

    public Route getTicket = (Request req, Response res) -> {
        int id = Integer.parseInt(req.params("id"));
        Ticket ticket = ticketService.getTicket(id);

        if (ticket == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Ticket not found");
            res.status(401);

            return errorResponse.toJson();
        }

        res.status(200);
        return gson.toJson(ticket);
    };

    public Route createTicket = (Request req, Response res) -> {
        String body = req.body();
        System.out.println("Got: " + body);
        JsonObject json = gson.fromJson(body, JsonObject.class);

        String departDate = (json.get("departDate").getAsString());
        String returnDate = (json.get("returnDate").getAsString());
        int companyId = (json.get("company").getAsInt());
        boolean oneWay = (json.get("oneWay").getAsBoolean());
        int flightId = (json.get("flightId").getAsInt());
        int count = (json.get("count").getAsInt());

        Flight flight = new Flight();
        flight.setId(flightId);
        Company company = new Company();
        company.setId(companyId);

        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setOneWay(oneWay);
        ticket.setDepartDate(Date.valueOf(departDate));
        if (returnDate.equals("")) {
            ticket.setReturnDate(null);
        } else {
            ticket.setReturnDate(Date.valueOf(returnDate));
        }
        ticket.setCompany(company);
        ticket.setCount(count);

        if (!ticketService.createTicket(ticket)) {
            res.status(500);
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while creating ticket");

            return  errorResponse.toJson();
        }

        List<Ticket> tickets = ticketService.getTickets();
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setTickets(tickets);

        res.status(201);
        return successResponse.toJson();
    };

    public Route updateTicket = (Request req, Response res) -> {
        String body = req.body();
        System.out.println("Got: " + body);
        JsonObject json = gson.fromJson(body, JsonObject.class);

        String departDate = (json.get("departDate").getAsString());
        String returnDate = (json.get("returnDate").getAsString());
        int companyId = (json.get("company").getAsInt());
        boolean oneWay = (json.get("oneWay").getAsBoolean());
        int id = (json.get("id").getAsInt());
        int flightId = (json.get("flightId").getAsInt());
        int count = (json.get("count").getAsInt());

        Flight flight = new Flight();
        flight.setId(flightId);
        Company company = new Company();
        company.setId(companyId);

        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setFlight(flight);
        ticket.setOneWay(oneWay);
        ticket.setDepartDate(Date.valueOf(departDate));
        ticket.setReturnDate(Date.valueOf(returnDate));
        ticket.setCompany(company);
        ticket.setCount(count);

        if (!ticketService.updateTicket(ticket)) {
            res.status(500);
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while updating ticket");

            return errorResponse.toJson();
        }

        List<Ticket> tickets = ticketService.getTickets();
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setTickets(tickets);

        res.status(200);
        return successResponse.toJson();
    };

    public Route deleteTicket = (Request req, Response res) -> {
        int ticketId = Integer.parseInt(req.params(":id"));

        if (!ticketService.deleteTicket(ticketId)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while deleting ticket");
            res.status(500);

            return errorResponse.toJson();
        }

        List<Ticket> tickets = ticketService.getTickets();
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setTickets(tickets);
        
        res.status(200);
        return successResponse.toJson();
    };
}
