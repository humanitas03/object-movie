package com.example.objectmovieapplication.screening.service

import com.example.objectmoviedomain.interfaces.store.ReservationRepository
import com.example.objectmoviedomain.interfaces.store.ScreeningRepository
import com.example.objectmoviedomain.logic.ScreeningManager
import com.example.objectmoviedomain.screen.Customer
import com.example.objectmoviedomain.screen.Reservation
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class ScreeningServiceImpl(
    private val screeningRepository: ScreeningRepository,
    private val reservationRepository: ReservationRepository
) : ScreeningManager(screeningRepository, reservationRepository) {

    // Transactional Proxy 적용을 위한 Overriding
    override fun reserve(customer: Customer?, audienceCount: Int, screeningId: UUID?): Reservation {
        return super.reserve(customer, audienceCount, screeningId)
    }
}
