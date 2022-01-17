package com.example.objectmovieapplication.service

import com.example.objectmovieapplication.screening.service.ScreeningServiceImpl
import com.example.objectmoviedomain.interfaces.store.MovieRepository
import com.example.objectmoviedomain.interfaces.store.ScreeningRepository
import com.example.objectmoviedomain.screen.Customer
import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.Movie
import com.example.objectmoviedomain.screen.NoneDiscountPolicy
import com.example.objectmoviedomain.screen.Screening
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.Duration
import java.time.LocalDateTime
import java.util.UUID

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScreeningServiceImplTest @Autowired constructor(
    val screeningServiceImpl: ScreeningServiceImpl,
    val movieRepository: MovieRepository,
    val screeningRepository: ScreeningRepository
) {

    private val testMovie = Movie(
        "title",
        Duration.ofMinutes(100L),
        Money.wons(1_000),
        NoneDiscountPolicy(),
    )

    private val testScreen = Screening(
        testMovie,
        10,
        LocalDateTime.now().minusDays(10)
    )

    private val testCustomer = Customer("testCustomer")

    @BeforeAll
    fun setUpData() {
        movieRepository.create(testMovie)
        screeningRepository.create(testScreen)
    }

    @Test
    @DisplayName("정상 예약 테스트")
    fun screeningServiceImplTest() {

        assertDoesNotThrow {
            val result = screeningServiceImpl.reserve(testCustomer, 1, testScreen.screeningId)
            println(result)
        }
    }

    @Test
    @DisplayName("예매 실패 - 존재하지 않는 상영 예매 시도")
    fun failScreeningServiceImplUnknownRequestTest() {
        assertThrows<NoSuchElementException> {
            screeningServiceImpl.reserve(testCustomer, 1, UUID.randomUUID())
        }
    }
}
