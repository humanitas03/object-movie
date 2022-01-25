package com.example.objectmovieapplication.store

import com.example.objectmoviedomain.interfaces.store.ScreeningRepository
import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.Movie
import com.example.objectmoviedomain.screen.NoneDiscountPolicy
import com.example.objectmoviedomain.screen.Screening
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.Duration
import java.time.LocalDateTime
import java.util.UUID

@SpringBootTest
@ActiveProfiles("test")
class ScreeningStoreImplTest {

    @Autowired
    lateinit var screeningRepository: ScreeningRepository

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("영속성 전이 테스트(screening > movie > discount-policy > discount-condition")
    fun persistenceCascadeTestwithSave() {
        val testMovie = Movie(
            "title",
            Duration.ofMinutes(100L),
            Money.wons(1_000),
            NoneDiscountPolicy(UUID.randomUUID()),
        )

        val testScreening = Screening(
            testMovie,
            10,
            LocalDateTime.now()
        )

        screeningRepository.create(testScreening)
        val result = screeningRepository.retrieveOne(testScreening.screeningId)

        println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result))
        assertNotNull(result.movie.discountPolicy)
        assertNotNull(result.movie.discountPolicy.conditions)
    }
}
