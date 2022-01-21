package com.example.objectmovieinfra.jpa.entities

import com.example.objectmoviedomain.screen.DiscountCondition
import com.example.objectmovieinfra.jpa.DiscountPolicyConditionJpaId
import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_discount_policy_condition")
@IdClass(DiscountPolicyConditionJpaId::class)
class DiscountPolicyConditionJpaEntity(
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    var id: Long? = null,

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "discount_policy_id", columnDefinition = "VARCHAR(38)")
    @JsonBackReference
    var discountPolicy: DiscountPolicyJpaEntity?,

    @Id
    @ManyToOne(targetEntity = DiscountConditionJpaEntity::class, fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JsonBackReference
    @JoinColumn(name = "discount_condition_id", columnDefinition = "VARCHAR(38)")
    var discountCondition: DiscountConditionJpaEntity?
) {
    companion object {
        fun fromDiscountPolicy(discountPolicy: DiscountPolicyJpaEntity?, discountCondition: DiscountCondition?): DiscountPolicyConditionJpaEntity {
            return DiscountPolicyConditionJpaEntity(
                discountPolicy = discountPolicy,
                discountCondition = discountCondition?.run {
                    DiscountConditionJpaEntity.from(this)
                }
            )
        }
    }
}
