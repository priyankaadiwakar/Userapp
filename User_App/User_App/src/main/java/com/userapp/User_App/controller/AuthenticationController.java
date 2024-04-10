package com.userapp.User_App.controller;

import com.userapp.User_App.model.User;
import com.userapp.User_App.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth/user")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    private Map<String, String> mapObj = new HashMap<String,String>();



    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user)
    {
        if(userService.register(user)!=null)
        {
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("useris not added", HttpStatus.CONFLICT);
    }

    public String generateToken(String username, String password) throws ServletException,Exception
    {
        String jwtToken ="";

        if(username==null || password == null)
        {
            throw  new ServletException("Please enter some username and password");
        }

        boolean flag= userService.Validate(username, password);
        if(!flag)
        {
            throw  new ServletException("Please enter a valid username and password");
        }
        else
        {
            jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis()+3000000)) //token will have limited time
                    .signWith(SignatureAlgorithm.HS256, "secret key").compact(); //pass the same secret key which have pass in claims

        }
        return jwtToken;


    }


    @PostMapping("/login")
    public ResponseEntity<?> performLogin(@RequestBody User user)
    {
        try
        {
            String jwtToken = generateToken(user.getUsername(), user.getPassword());
            mapObj.put("Message", "User Logged in successfully"); //to associate and map the message
            mapObj.put("jwtToken", jwtToken);
        }
        catch(Exception e)
        {
            mapObj.put("Message", "User Login  failed");
            mapObj.put("jwtToken", null);

            return new ResponseEntity<>(mapObj, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(mapObj, HttpStatus.OK);

    }
}
