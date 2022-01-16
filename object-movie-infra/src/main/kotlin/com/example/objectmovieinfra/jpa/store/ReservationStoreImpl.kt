package com.example.objectmovieinfra.jpa.store

import com.example.objectmoviedomain.interfaces.store.ReservationRepository
import com.example.objectmoviedomain.screen.Reservation
import com.example.objectmovieinfra.jpa.entities.ReservationJpaEntity
import com.example.objectmovieinfra.jpa.repository.ReservationJpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class ReservationStoreImpl(
    val jpaRepository: ReservationJpaRepository
) : ReservationRepository {
    override fun create(reservation: Reservation?) {
        reservation?.let {
            jpaRepository.save(ReservationJpaEntity.from(reservation))
        }
    }

    override fun retrieveOne(reservationId: UUID?): Reservation {
        return jpaRepository.findByReservationId(reservationId.toString())?.run {
            this.toDomainEntity()
        } ?: throw NoSuchElementException("예매 조회 실패 :  $reservationId")
    }
}
