package com.example.objectmoviedomain.screen.movie;

import com.example.objectmoviedomain.screen.DiscountCondition;
import com.example.objectmoviedomain.screen.Money;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class NoneDiscountMovie extends Movie {

    public NoneDiscountMovie(String title, Duration runningTime,
        Money fee,
        List<DiscountCondition> discountConditionList) {
        super(title, runningTime, fee, discountConditionList);
    }

    public NoneDiscountMovie(UUID movieId, String title, Duration runningTime,
        Money fee,
        List<DiscountCondition> discountConditionList) {
        super(movieId, title, runningTime, fee, discountConditionList);
    }

    @Override
    protected Money calculateDiscountAmount() {
        return Money.ZERO;
    }
}
