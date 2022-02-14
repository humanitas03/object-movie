package com.example.objectmoviedomain.logic;

import com.example.objectmoviedomain.screen.Customer;
import com.example.objectmoviedomain.screen.Reservation;
import com.example.objectmoviedomain.screen.Screening;
import com.example.objectmoviedomain.interfaces.service.ScreeningService;
import com.example.objectmoviedomain.interfaces.store.ReservationRepository;
import com.example.objectmoviedomain.interfaces.store.ScreeningRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScreeningManager {

    private final ScreeningRepository screeningRepository;
    private final ReservationRepository reservationRepository;

//    @Override
    public Reservation reserve(Customer customer, int audienceCount, UUID screeningId) {
        // 저장된 상영 정보를 로드
        Screening currentScreening = screeningRepository.retrieveOne(screeningId);

        // 예약 정보 저장
        reservationRepository.create(currentScreening.reserve(customer,audienceCount));
        return currentScreening.reserve(customer,audienceCount);
    }
}
