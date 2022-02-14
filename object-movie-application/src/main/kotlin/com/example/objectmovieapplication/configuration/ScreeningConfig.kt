package com.example.objectmovieapplication.configuration

import com.example.objectmoviedomain.interfaces.store.ReservationRepository
import com.example.objectmoviedomain.interfaces.store.ScreeningRepository
import com.example.objectmoviedomain.logic.ScreeningManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ScreeningConfig @Autowired constructor(
    val screeningRepository: ScreeningRepository,
    val reservationRepository: ReservationRepository
) {

    @Bean
    fun screeningManager(): ScreeningManager {
        return ScreeningManager(screeningRepository, reservationRepository)
    }
}
