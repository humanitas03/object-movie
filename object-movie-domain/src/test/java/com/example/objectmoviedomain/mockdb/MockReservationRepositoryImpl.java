package com.example.objectmoviedomain.mockdb;

import com.example.objectmoviedomain.screen.Reservation;
import com.example.objectmoviedomain.interfaces.store.ReservationRepository;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MockReservationRepositoryImpl implements ReservationRepository {

    Map<String, Reservation> mockReservationTable = new ConcurrentHashMap<>();

    @Override
    public void create(Reservation reservation) {
        mockReservationTable.putIfAbsent(reservation.getReservationId().toString(), reservation);
    }

    @Override
    public Reservation retrieveOne(UUID reservationId) {
        return mockReservationTable.get(reservationId.toString());
    }
}
