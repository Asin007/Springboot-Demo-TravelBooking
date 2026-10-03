package com.demo.app.trip.api;
import java.math.BigDecimal;

public record TripResponse (
    Long id,
    String title,
    String destination,
    BigDecimal price,
    String currency,
    int durationDays
){
    
    
}
