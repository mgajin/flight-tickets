package app.reservation;

import static spark.Spark.*;

public class ReservationRoutes {

    private final ReservationController controller;

    public ReservationRoutes(ReservationController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        get("app/reservations", controller.getReservations);
        get("app/reservations/:userId", controller.getUserReservations);
        post("app/reservations", controller.createReservation);
        delete("app/reservations/:id", controller.deleteReservation);
    }

}
