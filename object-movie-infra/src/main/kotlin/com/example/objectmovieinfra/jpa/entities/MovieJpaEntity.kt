package com.example.objectmovieinfra.jpa.entities

import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.Movie
import java.time.Duration
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tb_movie")
class MovieJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var movieId: String,

    @Column
    var movieTitle: String,

    @Column
    var runningTime: Long,

    @Column
    var fee: Long,
) {
    companion object {
        fun from(movie: Movie): MovieJpaEntity = MovieJpaEntity(
            id = null,
            movieId = movie.movieId.toString(),
            movieTitle = movie.title,
            runningTime = movie.runningTime.toMinutes(),
            fee = movie.fee.amount.toLong()
        )
    }

    fun toDomainEntity(): Movie {
        return Movie(
            UUID.fromString(movieId),
            movieTitle,
            Duration.ofMinutes(runningTime),
            Money.wons(fee)
        )
    }
}
