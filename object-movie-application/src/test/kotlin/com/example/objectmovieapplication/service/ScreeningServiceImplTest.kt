package com.example.objectmovieapplication.service

import com.example.objectmovieapplication.screening.service.ScreeningServiceImpl
import com.example.objectmoviedomain.interfaces.store.MovieRepository
import com.example.objectmoviedomain.interfaces.store.ScreeningRepository
import com.example.objectmoviedomain.screen.AmountDiscountPolicy
import com.example.objectmoviedomain.screen.Customer
import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.Movie
import com.example.objectmoviedomain.screen.NoneDiscountPolicy
import com.example.objectmoviedomain.screen.PercentDiscountPolicy
import com.example.objectmoviedomain.screen.PeriodCondition
import com.example.objectmoviedomain.screen.Screening
import com.example.objectmoviedomain.screen.SequenceCondition
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assumptions.assumingThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
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

    private val discountTestMovie1 = Movie(
        "discountTestMovie1",
        Duration.ofMinutes(100L),
        Money.wons(1_000),
        AmountDiscountPolicy(Money.wons(100L), SequenceCondition(1)),
    )

    private val discountTestMovie2 = Movie(
        "discountTestMovie2",
        Duration.ofMinutes(100L),
        Money.wons(1_000),
        AmountDiscountPolicy(Money.wons(100L), PeriodCondition(DayOfWeek.FRIDAY, LocalTime.of(10, 0, 0), LocalTime.of(22, 0, 0))),
    )

    private val discountTestMovie3 = Movie(
        "discountTestMovie3",
        Duration.ofMinutes(100L),
        Money.wons(1_000),
        PercentDiscountPolicy(0.1, SequenceCondition(1)),
    )

    private val discountTestMovie4 = Movie(
        "discountTestMovie4",
        Duration.ofMinutes(100L),
        Money.wons(1_000),
        PercentDiscountPolicy(0.1, PeriodCondition(DayOfWeek.FRIDAY, LocalTime.of(10, 0, 0), LocalTime.of(22, 0, 0))),
    )

    private val testScreen = Screening(
        testMovie,
        1,
        LocalDateTime.now().minusDays(10)
    )

    private val discountTestScreen1 = Screening(
        discountTestMovie1,
        1,
        LocalDateTime.now().minusDays(10)
    )

    private val discountTestScreen2 = Screening(
        discountTestMovie2,
        10,
        LocalDateTime.now()
    )

    private val discountTestScreen3 = Screening(
        discountTestMovie3,
        1,
        LocalDateTime.now().minusDays(10)
    )

    private val discountTestScreen4 = Screening(
        discountTestMovie4,
        10,
        LocalDateTime.now()
    )

    private val testCustomer = Customer("testCustomer")

    @BeforeAll
    fun setUpData() {
        movieRepository.create(testMovie)
        movieRepository.create(discountTestMovie1)
        movieRepository.create(discountTestMovie2)
        movieRepository.create(discountTestMovie3)
        movieRepository.create(discountTestMovie4)
        screeningRepository.create(testScreen)
        screeningRepository.create(discountTestScreen1)
        screeningRepository.create(discountTestScreen2)
        screeningRepository.create(discountTestScreen3)
        screeningRepository.create(discountTestScreen4)
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

    @Test
    @DisplayName("할인적용 테스트 - 정액할인 + 시퀀스, 기간 조건")
    fun amountDiscountTest() {

        val discountTestScreenResult = screeningRepository.retrieveOne(discountTestScreen1.screeningId)

        val reservation = screeningServiceImpl.reserve(testCustomer, 1, discountTestScreenResult.screeningId)

        val discountTestScreenResult2 = screeningRepository.retrieveOne(discountTestScreen2.screeningId)

        val reservation2 = screeningServiceImpl.reserve(testCustomer, 1, discountTestScreenResult2.screeningId)

        println(discountTestScreenResult)
        println(reservation)
        println(discountTestScreenResult2)
        println(reservation2)

        assertAll(
            { assertEquals(900L, reservation.fee.amount.longValueExact()) },
            {
                // 기간 할인 조건 (금요일, 10:00 ~ 22:22)에 만족하면 할인가격이 반환된다.
                assumingThat(reservation2.screening.movie.discountPolicy.conditions[0].isSatisfiedBy(reservation2.screening)) {
                    assertEquals(900L, reservation2.fee.amount.longValueExact())
                }
            },
            {
                // 기간 할인 조건 (금요일, 10:00 ~ 22:22)에 만족하지 않으면 일반 적용가격이 반환된다.
                assumingThat(!reservation2.screening.movie.discountPolicy.conditions[0].isSatisfiedBy(reservation2.screening)) {
                    assertEquals(1000L, reservation2.fee.amount.longValueExact())
                }
            },
        )
    }

    @Test
    @DisplayName("할인적용 테스트 - 정률할인 + 시퀀스, 기간 조건")
    fun percentPolicyDiscountTest() {

        val discountTestScreenResult3 = screeningRepository.retrieveOne(discountTestScreen3.screeningId)

        val reservation3 = screeningServiceImpl.reserve(testCustomer, 1, discountTestScreenResult3.screeningId)

        val discountTestScreenResult4 = screeningRepository.retrieveOne(discountTestScreen4.screeningId)

        val reservation4 = screeningServiceImpl.reserve(testCustomer, 1, discountTestScreenResult4.screeningId)

        println(discountTestScreenResult3)
        println(reservation3)
        println(discountTestScreenResult4)
        println(reservation4)

        assertAll(
            { assertEquals(900L, reservation3.fee.amount.longValueExact()) },
            {
                // 기간 할인 조건 (금요일, 10:00 ~ 22:22)에 만족하면 할인가격이 반환된다.
                assumingThat(reservation4.screening.movie.discountPolicy.conditions[0].isSatisfiedBy(reservation4.screening)) {
                    assertEquals(900L, reservation4.fee.amount.longValueExact())
                }
            },
            {
                // 기간 할인 조건 (금요일, 10:00 ~ 22:22)에 만족하지 않으면 일반 적용가격이 반환된다.
                assumingThat(!reservation4.screening.movie.discountPolicy.conditions[0].isSatisfiedBy(reservation4.screening)) {
                    assertEquals(1000L, reservation4.fee.amount.longValueExact())
                }
            },
        )
    }
}
