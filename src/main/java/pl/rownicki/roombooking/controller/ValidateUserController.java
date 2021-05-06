package pl.rownicki.roombooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rownicki.roombooking.service.JWTService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/basicAuth")
public class ValidateUserController {

    @Autowired
    JWTService jwtService;


    @RequestMapping("validate")
    public Map<String, String> userIsValid(HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        String name = currentUser.getUsername();
        String role = currentUser.getAuthorities().toArray()[0].toString().substring(5);

        String token = jwtService.genereteToken(name, role);
        Map<String, String> results = new HashMap<>();
        results.put("result", "ok");

        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/api/v1");
        cookie.setHttpOnly(true);
//      TODO: When in production must do cookie.setSecure(true);
        cookie.setMaxAge(1800);
        response.addCookie(cookie);
        return results;
    }
}
