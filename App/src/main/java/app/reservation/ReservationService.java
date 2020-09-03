package app.reservation;

import app.database.Dao;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {
    private final Dao<Reservation> repository;

    public ReservationService(Dao<Reservation> repository) {
        this.repository = repository;
    }

    public List<Reservation> getReservations() {
        return repository.getAll();
    }

    public List<Reservation> getUserReservations(int userId) {
        return repository
            .getAll()
            .stream()
            .filter(reservation -> reservation.getUser() == userId)
            .collect(Collectors.toList());
    }

    public boolean addReservation(Reservation reservation) {
        return repository.insert(reservation);
    }

    public boolean deleteReservation(int id) {
        String query = "DELETE FROM reservations WHERE id = (?)";
        return repository.delete(query, id);
    }
}