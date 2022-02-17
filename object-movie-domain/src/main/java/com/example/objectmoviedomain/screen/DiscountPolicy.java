package com.example.objectmoviedomain.screen;

import java.util.*;
import lombok.Getter;

@Deprecated
@Getter
public abstract class DiscountPolicy {
    private UUID discountPolicyId;
    private List<DiscountCondition> conditions = new ArrayList<>();

    public DiscountPolicy() {
        this.discountPolicyId = UUID.randomUUID();
    }

    public DiscountPolicy(DiscountCondition ... conditions) {
        this.conditions = Arrays.asList(conditions);
        this.discountPolicyId = UUID.randomUUID();
    }

    public DiscountPolicy(UUID discountPolicyId, DiscountCondition ... conditions) {
        this.conditions = Arrays.asList(conditions);
        this.discountPolicyId = discountPolicyId;
    }

    public Money calculateDiscountAmount(Screening screening) {
        for(DiscountCondition each : conditions) {
            if (each.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }

        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);
}
