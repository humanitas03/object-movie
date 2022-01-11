package com.example.objectmoviedomain.logic;

import com.example.objectmoviedomain.screen.Customer;
import com.example.objectmoviedomain.screen.Reservation;
import com.example.objectmoviedomain.screen.Screening;
import com.example.objectmoviedomain.service.ScreeningService;
import com.example.objectmoviedomain.store.ReservationRepository;
import com.example.objectmoviedomain.store.ScreeningRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScreeningManager implements ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public Reservation reserve(Customer customer, int audienceCount, UUID screeningId) {
        // 저장된 상영 정보를 로드
        Screening currentScreening = screeningRepository.retrieveOne(screeningId);

        // 예약 정보 저장
        reservationRepository.create(currentScreening.reserve(customer,audienceCount));
        return currentScreening.reserve(customer,audienceCount);
    }
}
