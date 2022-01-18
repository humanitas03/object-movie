package com.example.objectmovieinfra.jpa.entities

import com.example.objectmoviedomain.screen.DiscountCondition
import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.Entity
import javax.persistence.FetchType
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discount_policy_id")
    @JsonBackReference
    var discountPolicy: DiscountPolicyJpaEntity?,

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "discount_condition_id")
    var discountCondition: DiscountConditionJpaEntity?
) {
    companion object {
        fun fromDiscountPolicy(discountPolicy: DiscountPolicyJpaEntity?, discountCondition: DiscountCondition?): DiscountPolicyConditionJpaEntity {
            return DiscountPolicyConditionJpaEntity(
                id = null,
                discountPolicy = discountPolicy,
                discountCondition = discountCondition?.run {
                    DiscountConditionJpaEntity.from(this)
                }
            )
        }
    }
}
