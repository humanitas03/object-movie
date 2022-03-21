package com.example.objectmovieapplication.screening.controller

import com.example.objectmoviedomain.locator.ServiceLifecycle
import com.example.objectmoviedomain.screen.Customer
import com.example.objectmoviedomain.screen.Reservation
import kotlinx.serialization.Serializable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/screening")
class ScreeningController(
    val serviceLifecycle: ServiceLifecycle
) {

    @PostMapping("/reservation")
    fun registerReservation(
        @RequestBody reservationRequest: ReservationRequest
    ): Reservation {
        return serviceLifecycle.provideScreeningService().reserve(Customer(reservationRequest.customerName), reservationRequest.audienceCount, UUID.fromString(reservationRequest.screeningId))
    }
}

@Serializable
data class ReservationRequest(
    val screeningId: String,
    val customerName: String,
    val audienceCount: Int
)
