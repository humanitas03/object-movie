package com.example.objectmoviedomain.screen;

import java.util.UUID;
import lombok.Getter;

@Deprecated
@Getter
public class PercentDiscountPolicy extends DiscountPolicy{

    private double percent;

    public PercentDiscountPolicy(double percent, DiscountCondition ... conditions) {
        super(conditions);
        this.percent = percent;
    }

    public PercentDiscountPolicy(UUID discountPolicyId, double percent,  DiscountCondition ... conditions) {
        super(discountPolicyId, conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovie().getFee().times(percent);
    }
}
