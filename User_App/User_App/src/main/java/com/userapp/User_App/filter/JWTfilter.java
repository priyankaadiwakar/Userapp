package com.userapp.User_App.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JWTfilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        //creating objects
        HttpServletRequest httpreq= (HttpServletRequest) servletRequest;
        HttpServletResponse httpres =(HttpServletResponse) servletResponse;

        //we will check few things in a request
        //authorization header, while the http request will send what does the header contain,header needs to contain authorization as attribute
         String authHeader=httpreq.getHeader("authorization");

         //if the authheader is equal to null that is request is not come with any header or if the authheader variable has come with authorization heade but value is not starting with value "Bearer"
        if(authHeader != null || !authHeader.startsWith("Bearer")){
            throw new ServletException("Missing or Invalid Authorization header");

        }

        //try to capture token for that
        String jwtToken= authHeader.substring(7);

        //why this 7? Bearerey49icndos.oiidjc.09ilkkl==1st 6 letter from authorization type(Oauth,basic,bearer,apikey ) part from bearer long token is genertaed and stor in jwttoken
        // claim an associte this token body with a secrete key
        //whatever you write here you have to write the same thing while your generating token ..exact thing uppercase lower case
        Claims claims= Jwts.parser().setSigningKey("secret key").parseClaimsJws(jwtToken).getBody();

        httpreq.setAttribute("username", claims);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
