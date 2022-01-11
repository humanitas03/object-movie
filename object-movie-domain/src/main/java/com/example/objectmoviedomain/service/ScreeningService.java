package com.example.objectmoviedomain.service;

import com.example.objectmoviedomain.screen.Customer;
import com.example.objectmoviedomain.screen.Reservation;
import java.util.UUID;

public interface ScreeningService {
    Reservation reserve(Customer customer, int audienceCount, UUID screeningId);
}
