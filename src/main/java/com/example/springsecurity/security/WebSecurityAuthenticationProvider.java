package com.example.springsecurity.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class WebSecurityAuthenticationProvider implements AuthenticationProvider {
    //Authentication Logic

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      String username= authentication.getName();
      String password= authentication.getCredentials().toString();
      if(username.equals("jack") && password.equals("ma"))
      {
          return new UsernamePasswordAuthenticationToken(username,password, Arrays.asList());
      }
      else
          throw new  BadCredentialsException("UserName and password does not match");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // check if Authentciation Provider logic is supported or not by Authentication Manager
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
