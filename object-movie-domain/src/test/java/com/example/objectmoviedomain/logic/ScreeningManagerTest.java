package com.example.objectmoviedomain.logic;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.objectmoviedomain.mockdb.MockReservationRepositoryImpl;
import com.example.objectmoviedomain.mockdb.MockScreeningRepositoryImpl;
import com.example.objectmoviedomain.screen.Customer;
import com.example.objectmoviedomain.screen.Money;
import com.example.objectmoviedomain.screen.Movie;
import com.example.objectmoviedomain.screen.NoneDiscountPolicy;
import com.example.objectmoviedomain.screen.Screening;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ScreeningManagerTest {

    @Test
    @DisplayName("영화 예매 테스트")
    public void reservationTest() {
        var screeningRepo = new MockScreeningRepositoryImpl();
        var reservationRepo = new MockReservationRepositoryImpl();

        var testScreening = new Screening(new Movie("sample", Duration.ofMinutes(100L), Money.wons(100), new NoneDiscountPolicy()), 1, LocalDateTime.now());
        screeningRepo.create(testScreening);

        var manager = new ScreeningManager(
                screeningRepo,
                reservationRepo
        );

        var testCustomer = new Customer("tester");
        var result = manager.reserve(testCustomer, 10, testScreening.getScreeningId());
        log.info("result : " + result);
        assertAll(
                ()->assertNotNull(result),
                ()->assertNotNull(result.getScreening())
        );
    }
}
