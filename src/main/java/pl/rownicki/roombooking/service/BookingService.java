package pl.rownicki.roombooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rownicki.roombooking.data.BookingRepository;
import pl.rownicki.roombooking.exception.BookingNotFoundException;
import pl.rownicki.roombooking.model.Booking;

import java.sql.Date;
import java.util.List;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    public List<Booking> getBookingsByDate(String date) {
        System.out.println(date);
        Date selectedDate = Date.valueOf(date);
        return bookingRepository.findAllByDate(selectedDate);

    }

    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

    public Booking findById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("The booking does not exist."));
    }

    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking editBooking(Booking booking) {
        Booking updatedBooking = bookingRepository.findById(booking.getId())
                .orElseThrow(() -> new BookingNotFoundException("There is no booking with this ID"));
        return bookingRepository.save(booking);
    }
}
