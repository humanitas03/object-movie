package com.example.objectmovieapplication.store

import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.PeriodCondition
import com.example.objectmoviedomain.screen.SequenceCondition
import com.example.objectmoviedomain.screen.movie.AmountDiscountMovie
import com.example.objectmoviedomain.screen.movie.NoneDiscountMovie
import com.example.objectmoviedomain.screen.movie.PercentDiscountMovie
import com.example.objectmovieinfra.jpa.store.MovieStoreImpl
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalTime
import javax.persistence.EntityManagerFactory

@SpringBootTest
@ActiveProfiles("test")
class MovieStoreImplTest @Autowired constructor(
    val movieStoreImpl: MovieStoreImpl,
    val emf: EntityManagerFactory,
    var objectMapper: ObjectMapper
) {

    @Test
    @DisplayName("정상 등록 확인 - 할인 정책 없음.")
    fun saveTest() {
        val testMovie = NoneDiscountMovie(
            "title",
            Duration.ofMinutes(100L),
            Money.wons(1_000),
            null
        )

        val em = emf.createEntityManager()

        em.transaction.begin()
        movieStoreImpl.create(testMovie)
        em.transaction.commit()

        em.clear()

        movieStoreImpl.find(testMovie.movieId.toString()).let {
            assertNotNull(it)
            println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(it))
            assertEquals(testMovie.movieId, it.movieId)
        }
    }

    @Test
    @DisplayName("정상 등록 확인 - 비율 할인 정책 + 시퀀스 조건")
    fun saveTestPercentagePolicy() {
        val testMovie = PercentDiscountMovie(
            "PercentDiscountMovie",
            Duration.ofMinutes(100L),
            Money.wons(1_000),
            10.0,
            listOf(SequenceCondition(1))
        )

        val em = emf.createEntityManager()

        em.transaction.begin()
        movieStoreImpl.create(testMovie)
        em.transaction.commit()

        em.clear()

        movieStoreImpl.find(testMovie.movieId.toString()).let {
            assertNotNull(it)
            println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(it))
            assertEquals(testMovie.movieId, it.movieId)
        }
    }

    @Test
    @DisplayName("정상 등록 확인 - 정액할인 정책 + 기간 할인 조건")
    fun saveTestAmountPolicy() {
        val testMovie = AmountDiscountMovie(
            "AmountDiscountMovie",
            Duration.ofMinutes(100L),
            Money.wons(1_000),
            Money.wons(100),
            listOf(PeriodCondition(DayOfWeek.FRIDAY, LocalTime.NOON, LocalTime.MIDNIGHT))
        )

        val em = emf.createEntityManager()

        em.transaction.begin()
        movieStoreImpl.create(testMovie)
        em.transaction.commit()
        em.clear()

        movieStoreImpl.find(testMovie.movieId.toString()).let {
            assertNotNull(it)
            println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(it))
            assertEquals(testMovie.movieId, it.movieId)
        }
    }
}
