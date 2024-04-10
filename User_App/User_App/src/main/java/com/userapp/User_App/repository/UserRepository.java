package com.userapp.User_App.repository;

import com.userapp.User_App.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {

   @Query(value = "select u from User u where u.username= :username and u.password= :password")
   public User LoginUser(String username, String password);
}
