package app.reservation;

import static spark.Spark.get;

public class ReservationRoutes {

    private final ReservationController reservationController;

    public ReservationRoutes(ReservationController reservationController) {
        this.reservationController = reservationController;
        init();
    }

    private void init() {
        get("app/reservations", reservationController.getReservations);
        get("app/reservations/:user", reservationController.getUserReservations);
    }

}
