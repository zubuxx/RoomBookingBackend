package pl.rownicki.roombooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rownicki.roombooking.model.Booking;
import pl.rownicki.roombooking.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<Booking>> getBooking(@PathVariable("date") String date) {
        List<Booking> bookings = bookingService.getBookingsByDate(date);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping()
    public Booking getBooking(@RequestParam("id") Long id) {
        return bookingService.findById(id);
    }

    @PostMapping
    public Booking addBooking(@RequestBody Booking booking) throws InterruptedException {
        return bookingService.addBooking(booking);
    }

    @PutMapping
    public Booking updateBooking(@RequestBody Booking booking) {
        return bookingService.editBooking(booking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
