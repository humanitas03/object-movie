package com.example.objectmovieinfra.jpa.repository

import com.example.objectmovieinfra.jpa.entities.ScreeningJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScreeningJpaRepository : JpaRepository<ScreeningJpaEntity, Long> {
    fun findByScreeningId(screeningId: String): ScreeningJpaEntity?
}
