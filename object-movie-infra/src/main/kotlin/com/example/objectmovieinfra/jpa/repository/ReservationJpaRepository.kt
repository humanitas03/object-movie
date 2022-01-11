package com.example.objectmovieinfra.jpa.repository

import com.example.objectmovieinfra.jpa.entities.ReservationJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReservationJpaRepository : JpaRepository<ReservationJpaEntity, Long>
