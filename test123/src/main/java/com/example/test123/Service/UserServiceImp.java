package com.example.test123.Service;



import java.util.Optional;

import com.example.test123.Entity.Book;
import com.example.test123.Entity.User;
import com.example.test123.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImp implements UserSerivce {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }



    public Optional<User> findById(String email) {
        return userRepository.findById(email);
    }

    public User findByIduser(String email) {
        return userRepository.findById(email).get();
    }


    @Override
    public User checkLogin(String username, String password){
        Optional<User> optional = findById(username);
        User user = new User();
        if (optional.isPresent() && optional.get().getPassword().equals(password)) {
            user.setEmail(username);
            user.setPassword(password);
            user.setRole(optional.get().getRole());
            return user;
        }
        return null;
    }




}
