package com.demo.app.booking.api;

import java.time.LocalDate;


public record BookingRequest(
    Long tripId,
    LocalDate startDate,
    int guests
) {
    
}
