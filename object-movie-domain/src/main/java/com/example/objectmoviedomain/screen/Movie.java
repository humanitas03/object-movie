package com.example.objectmoviedomain.screen;

import java.time.Duration;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {
    private UUID movieId;
    private String title;
    private Duration runningTime;
    private Money fee;

    public Movie(String title, Duration runningTime, Money fee) {
        this.movieId = UUID.randomUUID();
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
    }

    public Movie(UUID movieId, String title, Duration runningTime, Money fee) {
        this.movieId = movieId;
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
    }

    public Money getFee() {
        return fee;
    }

    public Money calculateMovieFee(Screening screening) {
        // TODO : Not implemented
        return fee;
    }
}
