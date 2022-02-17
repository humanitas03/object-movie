package com.example.objectmoviedomain.screen.movie;

import com.example.objectmoviedomain.screen.DiscountCondition;
import com.example.objectmoviedomain.screen.Money;
import com.example.objectmoviedomain.screen.Screening;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public abstract class Movie {
    private UUID movieId;
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditionList;

    public Movie(String title, Duration runningTime, Money fee,
        List<DiscountCondition> discountConditionList) {
        this.movieId = UUID.randomUUID();
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountConditionList = discountConditionList;
    }

    public Movie(UUID movieId, String title, Duration runningTime, Money fee,
        List<DiscountCondition> discountConditionList) {
        this.movieId = movieId;
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountConditionList = discountConditionList;
    }

    public Money calculateMovieFee(Screening screening) {
        if (isDiscountable(screening)) {
            return fee.minus(calculateDiscountAmount());
        }

        return fee;
    }

    private boolean isDiscountable(Screening screening) {
        return discountConditionList.stream().anyMatch(condition -> condition.isSatisfiedBy(screening));
    }

    abstract protected Money calculateDiscountAmount();
}
