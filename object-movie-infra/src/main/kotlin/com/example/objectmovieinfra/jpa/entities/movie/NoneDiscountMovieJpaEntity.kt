package com.example.objectmovieinfra.jpa.entities.movie

import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.movie.NoneDiscountMovie
import com.example.objectmovieinfra.jpa.entities.DiscountConditionJpaEntity
import java.time.Duration
import java.util.UUID
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("NONE_DISCOUNT")
class NoneDiscountMovieJpaEntity(
    movieId: String,
    movieTitle: String,
    runningTime: Long,
    fee: Long,
    discountConditions: MutableList<DiscountConditionJpaEntity>?,
) : MovieJpaEntity(
    movieId,
    movieTitle,
    runningTime,
    fee,
    discountConditions
) {
    companion object {
        fun from(movie: NoneDiscountMovie): NoneDiscountMovieJpaEntity = NoneDiscountMovieJpaEntity(
            movieId = movie.movieId.toString(),
            movieTitle = movie.title,
            runningTime = movie.runningTime.toMinutes(),
            fee = movie.fee.amount.toLong(),
            discountConditions = movie.discountConditionList?.mapNotNull { DiscountConditionJpaEntity.from(it) }?.toMutableList()
        )
    }
    override fun toDomainEntity(): NoneDiscountMovie {
        return NoneDiscountMovie(
            UUID.fromString(movieId),
            movieTitle,
            Duration.ofMinutes(runningTime),
            Money.wons(fee),
            discountConditions?.map { it.toDomain() }
        )
    }
}
