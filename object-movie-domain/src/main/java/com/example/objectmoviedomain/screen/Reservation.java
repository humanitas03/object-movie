package com.example.objectmoviedomain.screen;

import java.util.UUID;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Reservation {
    private final UUID reservationId;
    private final Customer customer;
    private final Screening screening;
    private final Money fee;
    private final int audienceCount;

    public Reservation(Customer customer, Screening screening, Money fee, int audienceCount) {
        this.reservationId = UUID.randomUUID();
        this.customer = customer;
        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }

    public Reservation(UUID reservationId, Customer customer, Screening screening, Money fee, int audienceCount) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }
}
