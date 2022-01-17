package com.example.objectmovieinfra.jpa.entities

import com.example.objectmoviedomain.screen.Screening
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_screening")
class ScreeningJpaEntity(
    @Id
    @Column(length = 38)
    var screeningId: String,

    @Column
    var sequence: Int,

    @Column
    var whenScreened: LocalDateTime,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    var movie: MovieJpaEntity
) {
    companion object {
        fun from(screening: Screening): ScreeningJpaEntity {
            return ScreeningJpaEntity(
//                id = null,
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
