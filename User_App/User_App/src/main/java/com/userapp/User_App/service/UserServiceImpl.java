package com.userapp.User_App.service;

import com.userapp.User_App.model.User;
import com.userapp.User_App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        List<User> userList=userRepository.findAll();
        if(userList != null && userList.size()>=0){
            return userList;
        }
        return null;
    }

    @Override
    public User register(User user) {
        if(user != null){
            userRepository.saveAndFlush(user);
        }
        return null;
    }

    @Override
    public boolean Validate(String username, String password) {
        User user = userRepository.LoginUser(username, password);
        if(user !=null){
            return true;
        }
        return false;
    }
}
