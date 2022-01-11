package com.example.objectmovieinfra.jpa.entities

import com.example.objectmoviedomain.screen.Screening
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_screening")
class ScreeningJpaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var screeningId: String,

    @Column
    var sequence: Int,

    @Column
    var whenScreened: LocalDateTime,

    @OneToOne
    @JoinColumn(name = "movie_id")
    var movie: MovieJpaEntity
) {
    companion object {
        fun from(screening: Screening): ScreeningJpaEntity {
            return ScreeningJpaEntity(
                id = null,
                screeningId = screening.screeningId.toString(),
                sequence = screening.sequence,
                whenScreened = screening.whenScreened,
                movie = MovieJpaEntity.from(screening.movie)
            )
        }
    }

    fun toDomainEntity(): Screening {
        return Screening(
            UUID.fromString(screeningId),
            movie.toDomainEntity(),
            sequence,
            whenScreened
        )
    }
}
