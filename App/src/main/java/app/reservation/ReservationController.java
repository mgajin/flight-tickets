package app.reservation;

import app.flight.Flight;
import app.ticket.Ticket;
import app.utils.ErrorResponse;
import app.utils.SuccessResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class ReservationController {

    private ReservationService reservationService;

    private final Gson gson = new Gson();

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public Route getReservations = (Request req, Response res) -> {
        List<Reservation> reservations = reservationService.getReservations();

        if (reservations == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Reservations not found");
            res.status(404);
            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setReservations(reservations);

        res.status(200);
        return successResponse.toJson();
    };

    public Route getUserReservations = (Request req, Response res) -> {
        int user = Integer.parseInt(req.params("userId"));
        List<Reservation> reservations = reservationService.getUserReservations(user);

        if (reservations == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Reservations not found");
            res.status(404);
            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setReservations(reservations);

        res.status(200);
        return successResponse.toJson();
    };

    public Route createReservation = (Request req, Response res) -> {
        String body = req.body();
        JsonObject json = gson.fromJson(body, JsonObject.class);

        int user = json.get("user").getAsInt();
        int flightId = json.get("flight").getAsInt();
        int ticketId = json.get("ticket").getAsInt();

        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        Flight flight = new Flight();
        flight.setId(flightId);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setTicket(ticket);
        reservation.setFlight(flight);

        if (!reservationService.addReservation(reservation)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while creating reservation");
            res.status(501);
            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setReservations(reservationService.getUserReservations(user));

        res.status(201);
        return successResponse.toJson();
    };

    public Route deleteReservation = (Request req, Response res) -> {
        int id = Integer.parseInt(req.params("id"));

        if (!reservationService.deleteReservation(id)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while deleting reservation");
            res.status(501);
            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setReservations(reservationService.getReservations());

        res.status(200);
        return successResponse.toJson();
    };
}
