package com.example.objectmoviedomain.screen.movie;

import com.example.objectmoviedomain.screen.DiscountCondition;
import com.example.objectmoviedomain.screen.Money;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class PercentDiscountMovie extends Movie{
    private double percent;

    public PercentDiscountMovie(String title, Duration runningTime,
        Money fee,
        double percent,
        List<DiscountCondition> discountConditionList) {
        super(title, runningTime, fee, discountConditionList);
        this.percent = percent;
    }

    public PercentDiscountMovie(UUID movieId, String title, Duration runningTime,
        Money fee,
        double percent,
        List<DiscountCondition> discountConditionList) {
        super(movieId, title, runningTime, fee, discountConditionList);
        this.percent = percent;
    }

    @Override
    protected Money calculateDiscountAmount() {
        return getFee().times(percent);
    }
}
