package com.example.objectmoviedomain.interfaces.store;

import com.example.objectmoviedomain.screen.*;

public interface MovieRepository {
    void create(Movie movie);
    Movie find(String movieId);
    Movie update(String movieId, Movie movie);
}
