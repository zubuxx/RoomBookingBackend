package pl.rownicki.roombooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.rownicki.roombooking.data.UserRepository;
import pl.rownicki.roombooking.exception.UserNotFoundException;
import pl.rownicki.roombooking.model.AngularUser;
import pl.rownicki.roombooking.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        user.setPassword("a ");
        User updatedUser = userRepository.save(user);
        return new AngularUser(updatedUser);
    }

    public AngularUser addUser(User user) {
        User addedUser = userRepository.save(user);
        return new AngularUser(addedUser);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void resetPasswordById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with that ID does not exist."));
        user.setPassword("secret");
        this.userRepository.save(user);
    }

    public Map<String, String> getCurrentUserRole() {
        Collection<GrantedAuthority> roles = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String role = "";
        if (roles.size() > 0) {
            GrantedAuthority ga = roles.iterator().next();
            role = ga.getAuthority().substring(5);
        }
        Map<String,String> results = new HashMap<>();
        results.put("role", role);
        return results;
    }


}
