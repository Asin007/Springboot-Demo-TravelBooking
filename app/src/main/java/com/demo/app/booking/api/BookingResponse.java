package com.demo.app.booking.api;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookingResponse(
    Long bookingId,
    Long tripId,
    LocalDate startDate,
    int guests,
    BigDecimal totalPrice,
    String currency,
    String status
) {
    
}
