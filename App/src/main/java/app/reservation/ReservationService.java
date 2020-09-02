package app.reservation;

import app.database.Dao;

import java.util.List;

public class ReservationService {
    private final Dao<Reservation> repo;

    public ReservationService(Dao<Reservation> repo) {
        this.repo = repo;
    }

    public List<Reservation> getReservations() {
        return repo.getAll();
    }

    public boolean addReservation(Reservation reservation) {
        return repo.insert(reservation);
    }

    public boolean deleteReservation(Reservation reservation) {
        return repo.delete(reservation);
    }
}