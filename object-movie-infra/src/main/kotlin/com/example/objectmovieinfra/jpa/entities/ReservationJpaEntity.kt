package com.example.objectmovieinfra.jpa.entities

import com.example.objectmoviedomain.screen.Customer
import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.Reservation
import java.util.* // ktlint-disable no-wildcard-imports
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_reservation")
class ReservationJpaEntity(

    @Id
    @Column(length = 38)
    var reservationId: String,

    @Column
    var customerName: String,

    @Column
    var audienceCount: Int,

    @Column
    var fee: Long,

    @ManyToOne(cascade = [CascadeType.DETACH], fetch = FetchType.EAGER)
    @JoinColumn(name = "screening_id")
    var screening: ScreeningJpaEntity
) {
    companion object {
        fun from(reservation: Reservation): ReservationJpaEntity {
            return ReservationJpaEntity(
//                id = null,
                reservationId = reservation.reservationId.toString(),
                customerName = reservation.customer.name,
                audienceCount = reservation.audienceCount,
                fee = reservation.fee.amount.toLong(),
                screening = ScreeningJpaEntity.from(
                    reservation.screening
                )
            )
        }
    }

    fun toDomainEntity(): Reservation = Reservation(
        UUID.fromString(reservationId),
        Customer(customerName),
        screening.toDomainEntity(),
        Money.wons(fee),
        audienceCount
    )
}
