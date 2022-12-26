package com.example.test123.Service;

import com.example.test123.Entity.User;

import java.util.Optional;



public interface UserSerivce {
    void save(User user);
    User checkLogin(String username, String password);
    Optional<User> findById(String id);
    User findByIduser(String id);

}
