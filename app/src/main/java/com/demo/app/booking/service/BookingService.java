package com.demo.app.booking.service;
import com.demo.app.booking.api.BookingRequest;
import com.demo.app.booking.api.BookingResponse;
import com.demo.app.trip.api.TripResponse;
import com.demo.app.trip.service.TripService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class BookingService {
    private final TripService tripService;
    private final AtomicLong nextBookingId = new AtomicLong(1);
    private final Map<Long, BookingResponse> bookings = new ConcurrentHashMap<>();

    public BookingService(TripService tripService){
        this.tripService = tripService;
    }
    public optional<BookingResponse> create(BookingRequest request){
        if (request.guests()<1){
            throw new IllegalArgumentException("Guests must be at least 1");
        }
        if (request.startDate()==null || request.startDate().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Start date must be today or tomorrow");
        }
        Optional<TripResponse> matchingTrip = tripService.findById(request.tripId());
        if (matchingTrip.isEmpty()) {
            return Optional.empty();
        }

        TripResponse trip = matchingTrip.get();
        BigDecimal totalPrice = trip.price().multiply(BigDecimal.valueOf(request.guests()));
        long bookingId = nextBookingId.getAndIncrement();

        BookingResponse booking = new BookingResponse(
                bookingId,
                trip.id(),
                request.startDate(),
                request.guests(),
                totalPrice,
                trip.currency(),
                "PENDING"
        );

        bookings.put(bookingId, booking);
        return Optional.of(booking);
    }
    
}
