package com.example.objectmoviedomain.interfaces.store;

import com.example.objectmoviedomain.screen.Screening;
import java.util.UUID;

public interface ScreeningRepository {
    void create(Screening screening);
    Screening retrieveOne(UUID screeningId);
}
