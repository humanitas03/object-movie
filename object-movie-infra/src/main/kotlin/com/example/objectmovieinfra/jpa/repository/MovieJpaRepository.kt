package com.example.objectmovieinfra.jpa.repository

import com.example.objectmovieinfra.jpa.entities.movie.MovieJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieJpaRepository : JpaRepository<MovieJpaEntity, Long> {
    fun findByMovieId(movieId: String): MovieJpaEntity
}
