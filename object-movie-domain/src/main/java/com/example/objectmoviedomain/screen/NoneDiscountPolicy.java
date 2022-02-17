package com.example.objectmoviedomain.screen;

import java.util.UUID;

@Deprecated
public class NoneDiscountPolicy extends DiscountPolicy {

    public NoneDiscountPolicy(){
        super();
    }

    public NoneDiscountPolicy(UUID discountPolicyId) {
        super(discountPolicyId);
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
