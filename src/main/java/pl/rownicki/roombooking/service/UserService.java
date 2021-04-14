package pl.rownicki.roombooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rownicki.roombooking.data.UserRepository;
import pl.rownicki.roombooking.exception.UserNotFoundException;
import pl.rownicki.roombooking.model.AngularUser;
import pl.rownicki.roombooking.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<AngularUser> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new AngularUser(user)).collect(Collectors.toList());
    }

    public AngularUser findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User withj that ID does not exist."));
        return new AngularUser(user);
    }

    public AngularUser updateUser(User user) {
        User updatedUser = userRepository.save(user);
        return new AngularUser(updatedUser);
    }

    public AngularUser addUser(User user) {
        User addedUser = userRepository.save(user);
        return new AngularUser(addedUser);
    }

}
