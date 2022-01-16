package com.example.objectmovieinfra.jpa.entity

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
class ScreeningJpaEntityTest {

    @Test
    @DisplayName("Domain Entity -> JpaEntity 변환 테스트")
    fun toJpaEntityTest() {
//        assertDoesNotThrow {
//            val jpaEntity = ScreeningJpaEntity
//        }
    }
}
