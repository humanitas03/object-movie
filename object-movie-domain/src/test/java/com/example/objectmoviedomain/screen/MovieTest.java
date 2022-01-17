package com.example.objectmoviedomain.screen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovieTest {

    @Test
    @DisplayName("할인 정책 수정 테스트")
    public void changeDiscountPolicy() {
        var testMovie = new Movie("sample", Duration.ofMinutes(100L), Money.wons(100), new NoneDiscountPolicy(
                UUID.randomUUID()));
        assertEquals(NoneDiscountPolicy.class, testMovie.getDiscountPolicy().getClass());

        testMovie.changeDiscountPolicy(new PercentDiscountPolicy(10.0, new SequenceCondition(10)));
        assertEquals(PercentDiscountPolicy.class, testMovie.getDiscountPolicy().getClass());
    }
}
