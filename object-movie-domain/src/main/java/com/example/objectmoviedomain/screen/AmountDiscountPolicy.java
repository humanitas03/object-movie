package com.example.objectmoviedomain.screen;

import java.util.UUID;
import lombok.Getter;

@Getter
public class AmountDiscountPolicy extends DiscountPolicy{
    private Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition ... conditions) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    public AmountDiscountPolicy(UUID discountPolicyId, Money discountAmount, DiscountCondition ... conditions) {
        super(discountPolicyId, conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
