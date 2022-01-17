package com.example.objectmoviedomain.screen;

import java.time.*;
import java.util.UUID;
import lombok.Getter;

@Getter
public class PeriodCondition implements DiscountCondition{
    private UUID discountConditionId;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.discountConditionId = UUID.randomUUID();
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public PeriodCondition(UUID discountConditionId, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.discountConditionId = discountConditionId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return screening.getWhenScreened().getDayOfWeek().equals(dayOfWeek) &&
                startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
                endTime.compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
    }
}
