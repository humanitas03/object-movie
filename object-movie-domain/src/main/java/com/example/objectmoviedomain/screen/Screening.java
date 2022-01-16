package com.example.objectmoviedomain.screen;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Screening {

    private UUID screeningId;
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.screeningId = UUID.randomUUID();
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public Screening(UUID screeningId, Movie movie, int sequence, LocalDateTime whenScreened) {
        this.screeningId = screeningId;
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    // TODO : for jackson deserialize...
//    public LocalDateTime getStartTime() {
//        return whenScreened;
//    }

    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    // TODO : for jackson deserialize...
//    public Money getMovieFee() {
//        return movie.getFee();
//    }

    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    private Money calculateFee(int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount);
    }
}
