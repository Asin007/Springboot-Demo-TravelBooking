package com.demo.app.trip.service;

import com.demo.app.trip.api.TripResponse;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    public List<TripResponse> findAll(){
        return List.of(
            new TripResponse(
                1L,
                "coastal escape",
                "Lisbon, Portugal",
                new BigDecimal("849.00"),
                "EUR",
                5
            ),
            new TripResponse(
                2L,
                "Mountain retreat",
                "Banff, Canada",
                new BigDecimal("1299.00"),
                "CAD",
                7
            )
        );
    }
    public Optional<TripResponse> findById(Long id){
        return findAll().stream()
        .filter(trip -> trip.id().equals(id))
        .findFirst();
    }
    
}
