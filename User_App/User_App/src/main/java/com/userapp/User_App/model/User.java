package com.userapp.User_App.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    private int id;

    @Column(name="USERNAME")
    private String username;
    @Column(name="PASSWORD")
    private String password;
}
