package app.reservation;

import app.utils.ErrorResponse;
import app.utils.SuccessResponse;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class ReservationController {

    private ReservationService reservationService;

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
}
