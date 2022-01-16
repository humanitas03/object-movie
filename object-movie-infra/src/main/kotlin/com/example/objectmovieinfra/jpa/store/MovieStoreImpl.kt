package com.example.objectmovieinfra.jpa.store

import com.example.objectmoviedomain.interfaces.store.MovieRepository
import com.example.objectmoviedomain.screen.Movie
import com.example.objectmovieinfra.jpa.entities.MovieJpaEntity
import com.example.objectmovieinfra.jpa.repository.MovieJpaRepository
import org.springframework.stereotype.Repository

@Repository
class MovieStoreImpl(
    val jpaRepository: MovieJpaRepository
) : MovieRepository {
    override fun create(movie: Movie?) {
        movie?.let {
            jpaRepository.save(MovieJpaEntity.from(it))
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
