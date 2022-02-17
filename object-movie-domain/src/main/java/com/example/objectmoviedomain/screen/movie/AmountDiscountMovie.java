package com.example.objectmoviedomain.screen.movie;

import com.example.objectmoviedomain.screen.DiscountCondition;
import com.example.objectmoviedomain.screen.Money;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class AmountDiscountMovie extends Movie{
    private Money discountAmount;

    public AmountDiscountMovie(String title, Duration runningTime,
        Money fee,
        Money discountAmount,
        List<DiscountCondition> discountConditionList) {
        super(title, runningTime, fee, discountConditionList);
        this.discountAmount = discountAmount;
    }

    public AmountDiscountMovie(UUID movieId,
        String title, Duration runningTime,
        Money fee,
        Money discountAmount,
        List<DiscountCondition> discountConditionList) {
        super(movieId, title, runningTime, fee, discountConditionList);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money calculateDiscountAmount() {
        return this.discountAmount;
    }
}
