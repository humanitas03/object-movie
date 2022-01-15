package com.example.objectmovieapplication.store

import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.Movie
import com.example.objectmovieinfra.jpa.store.MovieStoreImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.Duration
import javax.persistence.EntityManagerFactory

@SpringBootTest
@ActiveProfiles("test")
class MovieStoreImplTest @Autowired constructor(
    val movieStoreImpl: MovieStoreImpl,
    val emf: EntityManagerFactory
) {

    @Test
    @DisplayName("")
    fun saveTest() {
        val testMovie = Movie(
            "title",
            Duration.ofMinutes(100L),
            Money.wons(1_000)
        )

        val em = emf.createEntityManager()

        em.transaction.begin()
        movieStoreImpl.create(testMovie)
        em.transaction.commit()

        em.clear()

        movieStoreImpl.find(testMovie.movieId.toString()).let {
            assertNotNull(it)
            assertEquals(testMovie.movieId, it.movieId)
        }
    }
}
