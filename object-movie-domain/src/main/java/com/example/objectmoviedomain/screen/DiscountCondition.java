package com.example.objectmoviedomain.screen;

import java.util.UUID;

public interface DiscountCondition {
    UUID getDiscountConditionId();
    boolean isSatisfiedBy(Screening screening);
}
