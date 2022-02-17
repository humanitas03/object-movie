package com.example.objectmovieinfra.jpa.entities.movie

import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.movie.AmountDiscountMovie
import com.example.objectmovieinfra.jpa.entities.DiscountConditionJpaEntity
import java.time.Duration
import java.util.UUID
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("AMOUNT_DISCOUNT")
class AmountDiscountMovieJpaEntity(
    movieId: String,
    movieTitle: String,
    runningTime: Long,
    fee: Long,
    discountConditions: MutableList<DiscountConditionJpaEntity>?,
    @Column(nullable = true)
    var discountAmount: Long
) : MovieJpaEntity(
    movieId,
    movieTitle,
    runningTime,
    fee,
    discountConditions
) {

    companion object {
        fun from(movie: AmountDiscountMovie): AmountDiscountMovieJpaEntity = AmountDiscountMovieJpaEntity(
            movieId = movie.movieId.toString(),
            movieTitle = movie.title,
            runningTime = movie.runningTime.toMinutes(),
            fee = movie.fee.amount.toLong(),
            discountAmount = movie.discountAmount.amount.toLong(),
            discountConditions = movie.discountConditionList.map { DiscountConditionJpaEntity.from(it) }.toMutableList()
        )
    }

    override fun toDomainEntity(): AmountDiscountMovie {
        return AmountDiscountMovie(
            UUID.fromString(movieId),
            movieTitle,
            Duration.ofMinutes(runningTime),
            Money.wons(discountAmount),
            Money.wons(fee),
            discountConditions?.map { it.toDomain() }
        )
    }
}
