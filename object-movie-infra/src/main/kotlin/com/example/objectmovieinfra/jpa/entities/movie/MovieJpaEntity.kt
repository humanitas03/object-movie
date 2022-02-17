package com.example.objectmovieinfra.jpa.entities.movie

import com.example.objectmoviedomain.screen.movie.AmountDiscountMovie
import com.example.objectmoviedomain.screen.movie.Movie
import com.example.objectmoviedomain.screen.movie.NoneDiscountMovie
import com.example.objectmoviedomain.screen.movie.PercentDiscountMovie
import com.example.objectmovieinfra.jpa.entities.DiscountConditionJpaEntity
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.DiscriminatorColumn
import javax.persistence.DiscriminatorType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "tb_movie")
@DiscriminatorColumn(
    name = "movie_type",
    discriminatorType = DiscriminatorType.STRING
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
sealed class MovieJpaEntity(

    @Id
    @Column(length = 38)
    open var movieId: String,

    @Column
    open var movieTitle: String,

    @Column
    open var runningTime: Long,

    @Column
    open var fee: Long,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(name = "tb_movie_discount_condition")
    open var discountConditions: MutableList<DiscountConditionJpaEntity>? = null
) {
    companion object {
        fun from(movie: Movie): MovieJpaEntity = when (movie) {
            is AmountDiscountMovie -> AmountDiscountMovieJpaEntity.from(movie)
            is PercentDiscountMovie -> PercentDiscountMovieJpaEntity.from(movie)
            is NoneDiscountMovie -> NoneDiscountMovieJpaEntity.from(movie)
            else -> throw NoSuchElementException("Movie 타입 오류")
        }
    }

    abstract fun toDomainEntity(): Movie
}
