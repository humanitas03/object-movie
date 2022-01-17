package com.example.objectmoviedomain.screen;

import java.util.UUID;
import lombok.Getter;

@Getter
public class SequenceCondition implements DiscountCondition{

    private UUID discountConditionId;
    private int sequence;

    public SequenceCondition(UUID discountConditionId, int sequence) {
        this.discountConditionId = discountConditionId;
        this.sequence = sequence;
    }

    public SequenceCondition(int sequence) {
        this.discountConditionId = UUID.randomUUID();
        this.sequence = sequence;
    }

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return false;
    }
}
