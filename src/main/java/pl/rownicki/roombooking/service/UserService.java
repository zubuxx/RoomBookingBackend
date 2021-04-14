package pl.rownicki.roombooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rownicki.roombooking.data.UserRepository;
import pl.rownicki.roombooking.exception.UserNotFoundException;
import pl.rownicki.roombooking.model.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User withj that ID does not exist."));
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

}
