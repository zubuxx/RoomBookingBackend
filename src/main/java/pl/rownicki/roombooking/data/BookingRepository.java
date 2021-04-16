package pl.rownicki.roombooking.data;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.rownicki.roombooking.model.Booking;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByDate(Date date);

}
