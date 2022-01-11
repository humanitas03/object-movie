package com.example.objectmoviedomain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.objectmoviedomain.screen.Money;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("정수 값 세팅 확인")
    public void longTypeInitTest() {
        Money testMoney = Money.wons(1_000);
        assertEquals(BigDecimal.valueOf(1_000), testMoney.getAmount());
    }

    @Test
    @DisplayName("소수점 포함 값 세팅 확인")
    public void doubleTypeInitTest() {
        Money testMoney = Money.wons(1_000.50);
        assertEquals(BigDecimal.valueOf(1_000.50), testMoney.getAmount());
    }
}
