package pl.rownicki.roombooking.data;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.rownicki.roombooking.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
