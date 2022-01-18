package com.example.objectmovieinfra.jpa.entities

import com.example.objectmoviedomain.screen.DiscountCondition
import com.example.objectmoviedomain.screen.PeriodCondition
import com.example.objectmoviedomain.screen.SequenceCondition
import com.example.objectmovieinfra.jpa.entities.enums.DiscountConditionType
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.DayOfWeek
import java.time.LocalTime
import java.util.* // ktlint-disable no-wildcard-imports
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import kotlin.NoSuchElementException

@Entity
@Table(name = "tb_discount_condition")
class DiscountConditionJpaEntity(

    @Id
    @Column(length = 38)
    var discountConditionId: String,

    @Column
    @Enumerated(EnumType.STRING)
    var discountConditionType: DiscountConditionType,

    @Column
    @Enumerated(EnumType.STRING)
    var dayOfWeek: DayOfWeek?,

    @Column
    var startTime: LocalTime?,

    @Column
    var endTime: LocalTime?,

    @Column
    var sequence: Int?,

    @OneToMany(mappedBy = "discountCondition", fetch = FetchType.LAZY)
    @JsonManagedReference
    var discountPolicyConditionJpaEntities: List<DiscountPolicyConditionJpaEntity>?
) {
    companion object {
        fun from(discountCondition: DiscountCondition): DiscountConditionJpaEntity {
            return when (discountCondition) {
                is SequenceCondition -> {
                    DiscountConditionJpaEntity(
                        discountConditionId = discountCondition.discountConditionId.toString(),
                        discountConditionType = DiscountConditionType.SEQUENCE,
                        sequence = discountCondition.sequence,
                        dayOfWeek = null,
                        startTime = null,
                        endTime = null,
                        discountPolicyConditionJpaEntities = null
                    ).apply {
                        this.discountPolicyConditionJpaEntities = listOf(DiscountPolicyConditionJpaEntity(null, null, this))
                    }
                }
                is PeriodCondition -> {
                    DiscountConditionJpaEntity(
                        discountConditionId = discountCondition.discountConditionId.toString(),
                        discountConditionType = DiscountConditionType.PERIOD,
                        sequence = null,
                        dayOfWeek = discountCondition.dayOfWeek,
                        startTime = discountCondition.startTime,
                        endTime = discountCondition.endTime,
                        discountPolicyConditionJpaEntities = null
                    ).apply {
                        this.discountPolicyConditionJpaEntities = listOf(DiscountPolicyConditionJpaEntity(null, null, this))
                    }
                }
                else -> throw RuntimeException("unexpected discountcondition type")
            }
        }
    }

    fun toSequenceDiscountConditionDomain(): SequenceCondition {
        return sequence?.run {
            SequenceCondition(
                UUID.fromString(discountConditionId),
                this
            )
        } ?: throw NoSuchElementException("fail to get SequenceCondition")
    }

    fun toPeriodDiscountConditionDomain(): PeriodCondition {
        return PeriodCondition(
            UUID.fromString(discountConditionId),
            dayOfWeek,
            startTime,
            endTime
        )
    }
}
