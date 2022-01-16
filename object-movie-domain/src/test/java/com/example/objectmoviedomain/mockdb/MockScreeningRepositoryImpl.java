package com.example.objectmoviedomain.mockdb;

import com.example.objectmoviedomain.screen.Screening;
import com.example.objectmoviedomain.interfaces.store.ScreeningRepository;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MockScreeningRepositoryImpl implements ScreeningRepository {

    Map<String, Screening> mockScreeningTable = new ConcurrentHashMap<>();

    @Override
    public void create(Screening screening) {
        mockScreeningTable.putIfAbsent(screening.getScreeningId().toString(), screening);
    }

    @Override
    public Screening retrieveOne(UUID screeningId) {
        return mockScreeningTable.get(screeningId.toString());
    }
}
