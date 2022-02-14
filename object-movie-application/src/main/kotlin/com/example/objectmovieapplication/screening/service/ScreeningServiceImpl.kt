package com.example.objectmovieapplication.screening.service

import com.example.objectmoviedomain.interfaces.service.ScreeningService
import com.example.objectmoviedomain.logic.ScreeningManager
import com.example.objectmoviedomain.screen.Customer
import com.example.objectmoviedomain.screen.Reservation
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class ScreeningServiceImpl(
    val screeningManager: ScreeningManager
) : ScreeningService {

    override fun reserve(customer: Customer?, audienceCount: Int, screeningId: UUID?): Reservation {
        return screeningManager.reserve(customer, audienceCount, screeningId)
    }
}
