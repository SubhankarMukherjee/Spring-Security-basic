package com.example.springsecurity.security;

import javax.servlet.*;
import java.io.IOException;

public class WebSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Before Filter");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("After filter");
    }

    //Note some other filters are GenericFilter
    //OncePerRequestFilter -excute only once this is interface

}
