package com.example.objectmovieapplication.store

import com.example.objectmoviedomain.interfaces.store.ScreeningRepository
import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.Screening
import com.example.objectmoviedomain.screen.SequenceCondition
import com.example.objectmoviedomain.screen.movie.AmountDiscountMovie
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.Duration
import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("local")
class ScreeningStoreImplTest {

    @Autowired
    lateinit var screeningRepository: ScreeningRepository

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("영속성 전이 테스트(screening > movie > discount-policy > discount-condition")
    fun persistenceCascadeTestwithSave() {
        val testMovie = AmountDiscountMovie(
            "title",
            Duration.ofMinutes(100L),
            Money.wons(1_000),
            Money.wons(100),
            listOf(SequenceCondition(1))
        )

        val testScreening = Screening(
            testMovie,
            10,
            LocalDateTime.now()
        )

        screeningRepository.create(testScreening)
        val result = screeningRepository.retrieveOne(testScreening.screeningId)

        println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result))
    }
}
