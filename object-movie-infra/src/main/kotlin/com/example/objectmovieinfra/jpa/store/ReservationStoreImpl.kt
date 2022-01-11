package com.example.objectmovieinfra.jpa.store

import com.example.objectmoviedomain.screen.Reservation
import com.example.objectmoviedomain.store.ReservationRepository
import com.example.objectmovieinfra.jpa.repository.ReservationJpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class ReservationStoreImpl(
    val jpaRepository: ReservationJpaRepository
) : ReservationRepository {
    override fun create(reservation: Reservation?) {
        TODO("Not yet implemented")
    }

    override fun retrieveOne(reservationId: UUID?): Reservation {
        TODO("Not yet implemented")
    }
}
