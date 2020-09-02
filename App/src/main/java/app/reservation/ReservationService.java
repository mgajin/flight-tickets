package app.reservation;

import app.database.Dao;

import java.util.List;

public class ReservationService {
    private final Dao<Reservation> repository;

    public ReservationService(Dao<Reservation> repository) {
        this.repository = repository;
    }

    public List<Reservation> getReservations() {
        return repository.getAll();
    }

    public List<Reservation> getUserReservations() {

        return repository.getAll();
    }

    public boolean addReservation(Reservation reservation) {
        return repository.insert(reservation);
    }

    public boolean deleteReservation(int id) {
        String query = "DELETE FROM reservations WHERE user = (?)";
        return repository.delete(query, id);
    }
}