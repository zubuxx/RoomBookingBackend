package pl.rownicki.roombooking.data;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rownicki.roombooking.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
