package com.userapp.User_App.controller;


import com.userapp.User_App.model.User;
import com.userapp.User_App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")

public class UserController {

    @Autowired
    private UserService us;

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers()
    {
        List<User> userlist = us.getAllUser();

        if(userlist !=null)
        {
            return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
        }
        return new ResponseEntity<String>("userlist is empty", HttpStatus.OK);


    }
}
