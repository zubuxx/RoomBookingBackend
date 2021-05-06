package pl.rownicki.roombooking.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.rownicki.roombooking.model.AngularUser;
import pl.rownicki.roombooking.model.User;
import pl.rownicki.roombooking.service.UserService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<AngularUser>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AngularUser> findUser(@PathVariable("id") Long id) {
        AngularUser user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<AngularUser> updateUser(@RequestBody AngularUser user) {
        return new ResponseEntity<>(userService.updateUser(user.asUser()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AngularUser> createUser(@RequestBody User user) {
        AngularUser newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteUserById(id);
    }

    @GetMapping("/resetPassword/{id}")
    public void resetPassword(@PathVariable("id") Long id) {
        userService.resetPasswordById(id);
    }

    @GetMapping("/currentUserRole")
    public Map<String, String> getCurrentUserRole(){
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
