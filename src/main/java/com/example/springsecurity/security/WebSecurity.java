package com.example.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    //note: otherPasswordEncoder test class we have some

    @Autowired
    WebSecurityAuthenticationProvider authenticationProvider;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.httpBasic();
        //form based login
        //http.formLogin(); // it will use a different authentication filter
       //only /admin will be authenticated othes will pass

        //use my filter before spring provided filter
        http.authorizeRequests().antMatchers("/api/v1/admin").authenticated();
        http.addFilterBefore(new WebSecurityFilter(), BasicAuthenticationFilter.class);

        //Nobbody can use below URL(forbidden Error even after correct username and password
       // http.authorizeRequests().antMatchers("/api/v1/admin").authenticated().anyRequest().denyAll();
    }

    // We not dont want In memory user details service because we have implemented Authnetication Provider
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      //  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//
//        userDetailsService.createUser( User.withUsername("jack").password(passwordEncoder.encode("ma")).authorities("read").build());
//        auth.userDetailsService(userDetailsService);//.passwordEncoder(passwordEncoder);
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
}
