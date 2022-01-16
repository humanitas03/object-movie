package com.example.objectmovieapplication.controller

import com.example.objectmovieapplication.screening.controller.ReservationRequest
import com.example.objectmoviedomain.interfaces.store.MovieRepository
import com.example.objectmoviedomain.interfaces.store.ScreeningRepository
import com.example.objectmoviedomain.screen.Customer
import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.Movie
import com.example.objectmoviedomain.screen.Reservation
import com.example.objectmoviedomain.screen.Screening
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.Duration
import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScreeningControllerTest @Autowired constructor(
    val movieRepository: MovieRepository,
    val screeningRepository: ScreeningRepository,
    val objectMapper: ObjectMapper,
    val mockMvc: MockMvc
) {

    private val testMovie = Movie(
        "title",
        Duration.ofMinutes(100L),
        Money.wons(1_000)
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
    @DisplayName("정상 예매 확인")
    fun successReservation() {
        val testReservationRequest = ReservationRequest(
            customerName = testCustomer.name,
            screeningId = testScreen.screeningId.toString(),
            audienceCount = 1
        )

        val result = mockMvc.perform(
            post("/screening/reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testReservationRequest))
        )
            .andExpect(status().isOk)
            .andExpect { content().contentType(MediaType.APPLICATION_JSON) }
            .andReturn()

        val resultReservation = objectMapper.readValue(result.response.contentAsString, Reservation::class.java)
        println(resultReservation)
    }
}
