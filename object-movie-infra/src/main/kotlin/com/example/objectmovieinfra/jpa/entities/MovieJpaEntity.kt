package com.example.objectmovieinfra.jpa.entities

import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.Movie
import java.time.Duration
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "tb_movie")
class MovieJpaEntity(

    @Id
    @Column(length = 38)
    var movieId: String,

    @Column
    var movieTitle: String,

    @Column
    var runningTime: Long,

    @Column
    var fee: Long,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(name = "tb_movie_discount_policy")
    var discountPolicies: List<DiscountPolicyJpaEntity>?
) {
    companion object {
        fun from(movie: Movie): MovieJpaEntity = MovieJpaEntity(
//            id = null,
            movieId = movie.movieId.toString(),
            movieTitle = movie.title,
            runningTime = movie.runningTime.toMinutes(),
            fee = movie.fee.amount.toLong(),
            discountPolicies = listOf(DiscountPolicyJpaEntity.from(movie.discountPolicy))
        )
    }

    fun toDomainEntity(): Movie {
        return Movie(
            UUID.fromString(movieId),
            movieTitle,
            Duration.ofMinutes(runningTime),
            Money.wons(fee),
            discountPolicies?.map { it.toAbstractDomain() }?.first()
        )
    }
}
