package com.example.objectmovieinfra.jpa.entities.movie

import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.movie.PercentDiscountMovie
import com.example.objectmovieinfra.jpa.entities.DiscountConditionJpaEntity
import java.math.BigDecimal
import java.time.Duration
import java.util.UUID
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("PERCENT_DISCOUNT")
class PercentDiscountMovieJpaEntity(
    movieId: String,
    movieTitle: String,
    runningTime: Long,
    fee: Long,
    discountConditions: MutableList<DiscountConditionJpaEntity>?,
    @Column(columnDefinition = "DECIMAL(11,4)", nullable = true)
    var percent: BigDecimal
) : MovieJpaEntity(
    movieId,
    movieTitle,
    runningTime,
    fee,
    discountConditions
) {

    companion object {
        fun from(movie: PercentDiscountMovie): PercentDiscountMovieJpaEntity = PercentDiscountMovieJpaEntity(
            movieId = movie.movieId.toString(),
            movieTitle = movie.title,
            runningTime = movie.runningTime.toMinutes(),
            fee = movie.fee.amount.toLong(),
            percent = BigDecimal.valueOf(movie.percent),
            discountConditions = movie.discountConditionList.map { DiscountConditionJpaEntity.from(it) }.toMutableList()
        )
    }

    override fun toDomainEntity(): PercentDiscountMovie {
        return PercentDiscountMovie(
            UUID.fromString(movieId),
            movieTitle,
            Duration.ofMinutes(runningTime),
            Money.wons(fee),
            percent.toDouble(),
            discountConditions?.map { it.toDomain() }
        )
    }
}
