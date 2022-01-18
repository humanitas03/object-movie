package com.example.objectmovieinfra.jpa.entities

import com.example.objectmoviedomain.screen.AmountDiscountPolicy
import com.example.objectmoviedomain.screen.DiscountCondition
import com.example.objectmoviedomain.screen.DiscountPolicy
import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.NoneDiscountPolicy
import com.example.objectmoviedomain.screen.PercentDiscountPolicy
import com.example.objectmovieinfra.jpa.entities.enums.DiscountConditionType
import com.example.objectmovieinfra.jpa.entities.enums.DiscountPolicyType
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.math.BigDecimal
import java.util.* // ktlint-disable no-wildcard-imports
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "tb_discount_policy")
class DiscountPolicyJpaEntity(

    @Id
    @Column(length = 38)
    var discountPolicyId: String,

    @Column
    @Enumerated(EnumType.STRING)
    var discountPolicyType: DiscountPolicyType,

    @Column(columnDefinition = "DECIMAL(11,4)", nullable = true)
    var percent: BigDecimal?,

    @Column(nullable = true, columnDefinition = "BIGINT(15)")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var amount: BigDecimal?,

    @OneToMany(mappedBy = "discountPolicy", fetch = FetchType.EAGER)
    @JsonManagedReference
    var discountPolicyConditionJpaEntities: List<DiscountPolicyConditionJpaEntity>?
) {
    companion object {
        fun from(discountPolicy: DiscountPolicy): DiscountPolicyJpaEntity {
            return when (discountPolicy) {
                is PercentDiscountPolicy -> {
                    DiscountPolicyJpaEntity(
                        discountPolicyId = discountPolicy.discountPolicyId.toString(),
                        discountPolicyType = DiscountPolicyType.PERCENT,
                        percent = BigDecimal.valueOf(discountPolicy.percent),
                        amount = null,
                        discountPolicyConditionJpaEntities = null
                    ).apply {
                        this.discountPolicyConditionJpaEntities = discountPolicy.conditions.map { DiscountPolicyConditionJpaEntity.fromDiscountPolicy(this, it) }
                    }
                }
                is AmountDiscountPolicy -> {
                    DiscountPolicyJpaEntity(
                        discountPolicyId = discountPolicy.discountPolicyId.toString(),
                        discountPolicyType = DiscountPolicyType.AMOUNT,
                        percent = null,
                        amount = discountPolicy.discountAmount.amount,
                        discountPolicyConditionJpaEntities = null
                    ).apply {
                        this.discountPolicyConditionJpaEntities = discountPolicy.conditions.map { DiscountPolicyConditionJpaEntity.fromDiscountPolicy(this, it) }
                    }
                }
                is NoneDiscountPolicy -> {
                    DiscountPolicyJpaEntity(
                        discountPolicyId = discountPolicy.discountPolicyId.toString(),
                        discountPolicyType = DiscountPolicyType.NONE,
                        percent = null,
                        amount = null,
                        discountPolicyConditionJpaEntities = null,
                    ).apply {
                        this.discountPolicyConditionJpaEntities = discountPolicy.conditions.map { DiscountPolicyConditionJpaEntity.fromDiscountPolicy(this, null) }
                    }
                }
                else -> throw RuntimeException("unexpected Discount Policy Type")
            }
        }
    }

//    fun toDomainEntity() : DiscountPolicy {
//        return when(discountPolicyType) {
//            DiscountPolicyType.PERCENT -> {
//                PercentDiscountPolicy(
//                    discountPolicyId,
//                    percent?.toDouble() ?: 0
//                )
//            }
//        }
//    }

    private fun getConditions(): Array<DiscountCondition> {
        return discountPolicyConditionJpaEntities?.map {
            when (it.discountCondition?.discountConditionType) {
                DiscountConditionType.PERIOD -> it.discountCondition!!.toPeriodDiscountConditionDomain()
                DiscountConditionType.SEQUENCE -> it.discountCondition!!.toSequenceDiscountConditionDomain()
                else -> throw RuntimeException("fail to get Conditions")
            }
        }?.toTypedArray() ?: emptyArray()
    }

    fun toPercentDomain(): PercentDiscountPolicy {
        if (discountPolicyType != DiscountPolicyType.PERCENT)
            throw RuntimeException("This is not percentDiscountPolicy")

        return PercentDiscountPolicy(
            UUID.fromString(discountPolicyId),
            percent?.toDouble() ?: Double.NaN,
            *getConditions()
        )
    }

    fun toAmountDomain(): AmountDiscountPolicy {
        if (discountPolicyType != DiscountPolicyType.AMOUNT)
            throw RuntimeException("This is not AmountDiscountPolicy")

        return AmountDiscountPolicy(
            UUID.fromString(discountPolicyId),
            Money.wons(amount?.longValueExact() ?: 0L),
            *getConditions()
        )
    }

    fun toNoneDomain(): NoneDiscountPolicy {
        if (discountPolicyType != DiscountPolicyType.NONE)
            throw RuntimeException("This is not NoneDiscountPolicy")

        return NoneDiscountPolicy(
            UUID.fromString(discountPolicyId)
        )
    }

    fun toAbstractDomain(): DiscountPolicy {
        return when (discountPolicyType) {
            DiscountPolicyType.AMOUNT -> toAmountDomain()
            DiscountPolicyType.NONE -> toNoneDomain()
            DiscountPolicyType.PERCENT -> toPercentDomain()
        }
    }

    @SuppressWarnings("unchecked")
    fun <T : DiscountPolicy> toGenericDomain(clazz: Class<out DiscountPolicy>): T {
        return when (clazz) {
            NoneDiscountPolicy::class.java -> toNoneDomain() as T
            AmountDiscountPolicy::class.java -> toAmountDomain() as T
            PercentDiscountPolicy::class.java -> toPercentDomain() as T
            else -> throw RuntimeException("DiscountPolicy Return fail")
        }
    }
}
