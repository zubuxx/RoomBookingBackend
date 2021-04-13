package pl.rownicki.roombooking.data;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rownicki.roombooking.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
