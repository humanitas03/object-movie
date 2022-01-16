package com.example.objectmovieinfra.jpa.store

import com.example.objectmoviedomain.interfaces.store.ScreeningRepository
import com.example.objectmoviedomain.screen.Screening
import com.example.objectmovieinfra.jpa.entities.ScreeningJpaEntity
import com.example.objectmovieinfra.jpa.repository.ScreeningJpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class ScreeningStoreImpl(
    val jpaRepository: ScreeningJpaRepository
) : ScreeningRepository {
    override fun create(screening: Screening?) {
        screening?.let {
            jpaRepository.save(ScreeningJpaEntity.from(it))
        }
    }

    override fun retrieveOne(screeningId: UUID?): Screening {
        return jpaRepository.findByScreeningId(screeningId.toString())?.toDomainEntity()
            ?: throw NoSuchElementException("상영 정보가 없습니다 : $screeningId")
    }
}
