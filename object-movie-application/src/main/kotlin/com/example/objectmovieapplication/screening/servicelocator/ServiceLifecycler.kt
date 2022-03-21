package com.example.objectmovieapplication.screening.servicelocator

import com.example.objectmoviedomain.interfaces.service.ScreeningService
import com.example.objectmoviedomain.locator.ServiceLifecycle
import org.springframework.stereotype.Component

@Component
class ServiceLifecycler constructor(
    val screeningService: ScreeningService
) : ServiceLifecycle {
    override fun provideScreeningService(): ScreeningService {
        return screeningService
    }
}
