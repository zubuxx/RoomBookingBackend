package pl.rownicki.roombooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rownicki.roombooking.service.JWTService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/basicAuth")
public class ValidateUserController {

    @Autowired
    JWTService jwtService;


    @RequestMapping("validate")
    public Map<String, String> userIsValid() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        String name = currentUser.getUsername();
        String role = currentUser.getAuthorities().toArray()[0].toString().substring(5);

        String token = jwtService.genereteToken(name, role);
        Map<String, String> results = new HashMap<>();
        results.put("result", token);
        return results;
    }
}
