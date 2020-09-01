package app.ticket;

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

    public static Route getTickets = (Request req, Response res) -> {
        QueryParamsMap queries = req.queryMap();
        List<Ticket> tickets;

        if (queries.hasKey("oneWay")) {
            boolean isOneWay = Boolean.parseBoolean(queries.get("oneWay").value());
            tickets = (isOneWay) ? TicketService.getOneWayTickets() : TicketService.getTwoWayTickets();
        } else {
            tickets = TicketService.getTickets();
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
            tickets = TicketService.getPaginated(tickets, pageInfo);
            successResponse.setPageInfo(pageInfo);
        }
        successResponse.setTickets(tickets);

        res.status(200);
        return successResponse.toJson();
    };

    public static Route getTicket = (Request req, Response res) -> {
        int id = Integer.parseInt(req.params("id"));
        Ticket ticket = TicketService.getTicket(id);

        if (ticket == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Ticket not found");
            res.status(401);

            return errorResponse.toJson();
        }

        res.status(200);
        return gson.toJson(ticket);
    };

    public static Route createTicket = (Request req, Response res) -> {
        String body = req.body();
        System.out.println("Got: " + body);
        JsonObject json = gson.fromJson(body, JsonObject.class);

        String departDate = (json.get("departDate").getAsString());
        String returnDate = (json.get("returnDate").getAsString());
        String companyName = (json.get("companyName").getAsString());
        boolean oneWay = (json.get("oneWay").getAsBoolean());
        int flightId = (json.get("flightId").getAsInt());
        int count = (json.get("count").getAsInt());

        Ticket ticket = new Ticket();
        ticket.setFlightId(flightId);
        ticket.setOneWay(oneWay);
        ticket.setDepartDate(Date.valueOf(departDate));
        ticket.setReturnDate(Date.valueOf(returnDate));
        ticket.setCompanyName(companyName);
        ticket.setCount(count);

        if (!TicketService.createTicket(ticket)) {
            res.status(500);
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while creating ticket");

            return  errorResponse.toJson();
        }

        List<Ticket> tickets = TicketService.getTickets();
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setTickets(tickets);

        res.status(201);
        return successResponse.toJson();
    };

    public static Route updateTicket = (Request req, Response res) -> {
        String body = req.body();
        System.out.println("Got: " + body);
        JsonObject json = gson.fromJson(body, JsonObject.class);

        String departDate = (json.get("departDate").getAsString());
        String returnDate = (json.get("returnDate").getAsString());
        String companyName = (json.get("companyName").getAsString());
        boolean oneWay = (json.get("oneWay").getAsBoolean());
        int id = (json.get("id").getAsInt());
        int flightId = (json.get("flightId").getAsInt());
        int count = (json.get("count").getAsInt());

        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setFlightId(flightId);
        ticket.setOneWay(oneWay);
        ticket.setDepartDate(Date.valueOf(departDate));
        ticket.setReturnDate(Date.valueOf(returnDate));
        ticket.setCompanyName(companyName);
        ticket.setCount(count);

        if (!TicketService.updateTicket(ticket)) {
            res.status(500);
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while updating ticket");

            return errorResponse.toJson();
        }

        List<Ticket> tickets = TicketService.getTickets();
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setTickets(tickets);

        res.status(200);
        return successResponse.toJson();
    };

    public static Route deleteTicket = (Request req, Response res) -> {
        int ticketId = Integer.parseInt(req.params(":id"));

        if (!TicketService.deleteTicket(ticketId)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while deleting ticket");
            res.status(500);

            return errorResponse.toJson();
        }

        List<Ticket> tickets = TicketService.getTickets();
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setTickets(tickets);
        
        res.status(200);
        return successResponse.toJson();
    };
}
