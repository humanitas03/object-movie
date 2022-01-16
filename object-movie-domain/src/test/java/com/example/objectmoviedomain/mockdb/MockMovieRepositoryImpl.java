package com.example.objectmoviedomain.mockdb;

import com.example.objectmoviedomain.screen.Movie;
import com.example.objectmoviedomain.interfaces.store.MovieRepository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MockMovieRepositoryImpl implements MovieRepository {

    Map<String, Movie> movieMockTable = new ConcurrentHashMap<>();

    @Override
    public void create(Movie movie) {
        movieMockTable.putIfAbsent(movie.getMovieId().toString(), movie);
    }

    @Override
    public Movie find(String movieId) {
        return movieMockTable.get(movieId);
    }

    @Override
    public Movie update(String movieId, Movie movie) {
        return movieMockTable.replace(movieId, movie);
    }
}
