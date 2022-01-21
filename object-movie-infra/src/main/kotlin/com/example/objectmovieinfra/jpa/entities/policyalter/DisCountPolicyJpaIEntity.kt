package com.example.objectmovieinfra.jpa.entities.policyalter

import com.example.objectmovieinfra.jpa.entities.DiscountPolicyConditionJpaEntity
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.Column
import javax.persistence.DiscriminatorColumn
import javax.persistence.DiscriminatorType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.OneToMany

@Entity
@DiscriminatorColumn(
    name = "discount_policy_type",
    discriminatorType = DiscriminatorType.STRING
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
open class DisCountPolicyJpaIEntity(

    @Id
    @Column(length = 38)
    var discountPolicyId: String? = null,

    @OneToMany(mappedBy = "discountPolicy", fetch = FetchType.EAGER)
    @JsonManagedReference
    var discountPolicyConditionJpaEntities: List<DiscountPolicyConditionJpaEntity>? = null
)
