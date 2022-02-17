package com.example.objectmovieinfra.jpa.store

import com.example.objectmoviedomain.interfaces.store.MovieRepository
import com.example.objectmoviedomain.screen.movie.AmountDiscountMovie
import com.example.objectmoviedomain.screen.movie.Movie
import com.example.objectmoviedomain.screen.movie.NoneDiscountMovie
import com.example.objectmoviedomain.screen.movie.PercentDiscountMovie
import com.example.objectmovieinfra.jpa.entities.movie.AmountDiscountMovieJpaEntity
import com.example.objectmovieinfra.jpa.entities.movie.NoneDiscountMovieJpaEntity
import com.example.objectmovieinfra.jpa.entities.movie.PercentDiscountMovieJpaEntity
import com.example.objectmovieinfra.jpa.repository.MovieJpaRepository
import org.springframework.stereotype.Repository

@Repository
class MovieStoreImpl(
    val jpaRepository: MovieJpaRepository
) : MovieRepository {
    override fun create(movie: Movie?) {
        movie?.let {
            when (it) {
                is AmountDiscountMovie -> jpaRepository.save(AmountDiscountMovieJpaEntity.from(it))
                is PercentDiscountMovie -> jpaRepository.save(PercentDiscountMovieJpaEntity.from(it))
                is NoneDiscountMovie -> jpaRepository.save(NoneDiscountMovieJpaEntity.from(it))
                else -> throw NoSuchElementException("Movie 타입 에러[${it.javaClass.simpleName}]")
            }
        }
    }

    override fun find(movieId: String?): Movie {
        return movieId?.run {
            jpaRepository.findByMovieId(this).toDomainEntity()
        } ?: throw NoSuchElementException("not found movie")
    }

    override fun update(movieId: String?, movie: Movie?): Movie {
        TODO("Not yet implemented")
    }
}
