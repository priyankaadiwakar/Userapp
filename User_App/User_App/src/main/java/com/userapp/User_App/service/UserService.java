package com.userapp.User_App.service;

import com.userapp.User_App.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUser();

    public User register(User user);
     public boolean Validate(String username,String password);
}
