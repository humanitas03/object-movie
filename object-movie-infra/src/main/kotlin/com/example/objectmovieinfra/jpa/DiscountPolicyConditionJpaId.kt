package com.example.objectmovieinfra.jpa

import java.io.Serializable

data class DiscountPolicyConditionJpaId(
    var discountPolicy: String? = null,
    var discountCondition: String? = null
) : Serializable
