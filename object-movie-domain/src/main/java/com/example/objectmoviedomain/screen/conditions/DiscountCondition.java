package com.example.objectmoviedomain.screen.conditions;

import com.example.objectmoviedomain.screen.Screening;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;
import lombok.Getter;

@Getter
public class DiscountCondition {

    private UUID discountConditionId;
    private DiscountConditionType type;
    private int sequence;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public DiscountCondition(DiscountConditionType type, int sequence, DayOfWeek dayOfWeek,
        LocalTime startTime, LocalTime endTime) {
        this.type = type;
        this.sequence = sequence;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DiscountCondition(UUID discountConditionId,
        DiscountConditionType type, int sequence, DayOfWeek dayOfWeek, LocalTime startTime,
        LocalTime endTime) {
        this.discountConditionId = discountConditionId;
        this.type = type;
        this.sequence = sequence;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isSatisfiedBy(Screening screening) {
        if (type == DiscountConditionType.PERIOD) {
            return isSatisfiedByPeriod(screening);
        }

        return isSatisfiedBySequence(screening);
    }

    private boolean isSatisfiedByPeriod(Screening screening) {
        return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek()) &&
        startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
        !endTime.isAfter(screening.getWhenScreened().toLocalTime());
    }

    private boolean isSatisfiedBySequence(Screening screening) {
        return sequence == screening.getSequence();
    }
}
