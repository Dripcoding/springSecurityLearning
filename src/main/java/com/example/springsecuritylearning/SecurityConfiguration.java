package com.example.springsecuritylearning;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
}
