package com.example.objectmovieinfra.jpa

import com.example.objectmoviedomain.screen.DiscountPolicy

// TODO : not yet used
data class DiscountPoliciesWrapper<T : DiscountPolicy> (
    var genericDomain: T
)
