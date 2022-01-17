package com.example.objectmovieinfra.jpa.entities

import com.example.objectmoviedomain.screen.DiscountCondition
import com.example.objectmoviedomain.screen.DiscountPolicy
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_discount_policy_condition")
class DiscountPolicyConditionJpaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "discount_policy_id")
    var discountPolicy: DiscountPolicyJpaEntity?,

    @ManyToOne
    @JoinColumn(name = "discount_condition_id")
    var discountCondition: DiscountConditionJpaEntity?
) {
    companion object {
        fun fromDiscountPolicy(discountPolicy: DiscountPolicy?, discountCondition: DiscountCondition?): DiscountPolicyConditionJpaEntity {
            return DiscountPolicyConditionJpaEntity(
                id = null,
                discountPolicy = discountPolicy?.run {
                    DiscountPolicyJpaEntity.from(this)
                },
                discountCondition = discountCondition?.run {
                    DiscountConditionJpaEntity.from(this)
                }
            )
        }
    }
}
