package com.example.objectmoviedomain.interfaces.store;

import com.example.objectmoviedomain.screen.DiscountPolicy;

public interface DiscountPolicyRepository {
    void create(DiscountPolicy discountPolicy);
    DiscountPolicy retrieve(String discountPolicyId);
}
