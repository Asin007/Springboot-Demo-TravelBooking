package com.demo.app.trip.api;

import com.demo.app.trip.service.TripService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/trips")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService){
        this.tripService= tripService;
    }
    @GetMapping
    public List<TripResponse> findAll(){
        return tripService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TripResponse> findById(@PathVariable Long id){
        return ResponseEntity.of(tripService.findById(id));
    }
    
}
