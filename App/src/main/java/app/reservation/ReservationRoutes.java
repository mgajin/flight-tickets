package app.reservation;

import static spark.Spark.get;
import static spark.Spark.post;

public class ReservationRoutes {

    private final ReservationController controller;

    public ReservationRoutes(ReservationController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        get("app/reservations", controller.getReservations);
        get("app/reservations/:user", controller.getUserReservations);
        post("app/reservations", controller.createReservation);
    }

}
