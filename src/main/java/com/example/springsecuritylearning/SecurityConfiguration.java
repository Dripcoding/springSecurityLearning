package com.example.springsecuritylearning;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// spring-security provides WebSecurityConfigurerAdapter as a means to provide custom configuration
// configure() method allows devs to configure behavior of AuthenticationManager via AuthenticationManagerBuilder

@EnableWebSecurity // tell spring-security that this is a web-security configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // spring-security requires a password encoder!
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // will not encode passwords
    }

    // for authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // set your configuration
        auth.inMemoryAuthentication()
                .withUser("blah")
                .password("blah")
                .roles("USER")
                .and()
                .withUser("foo")
                .password("foo")
                .roles("ADMIN");
    }

    // for authorization
        // antMatchers - use regex and wild-cards to define path patterns
        // hasRole(String role) - assign paths to a specific role
        // hasAnyRole(...) - assign many roles
    // order from Most restrictive to the Least restrictive
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }
}
