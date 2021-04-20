package pl.rownicki.roombooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("kacper").password("{noop}secret").authorities("ROLE_ADMIN")
                .and()
                .withUser("jane").password("{noop}secret").authorities("ROLE_USER");


        // TODO: this passwords should be encoded.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/v1/basicAuth/**").permitAll()
                .antMatchers("/api/v1/basicAuth/**").hasAnyRole("ADMIN", "USER")
                .and()
                .httpBasic();

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/v1/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/bookings/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/v1/**").hasRole("ADMIN")
                .and()
                .addFilter(new JWTFilter(authenticationManager()));
    }


}
