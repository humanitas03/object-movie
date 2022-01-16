package com.example.objectmoviedomain.interfaces.store;

import com.example.objectmoviedomain.screen.Reservation;
import java.util.UUID;

public interface ReservationRepository {
    void create(Reservation reservation);
    Reservation retrieveOne(UUID reservationId);
}
