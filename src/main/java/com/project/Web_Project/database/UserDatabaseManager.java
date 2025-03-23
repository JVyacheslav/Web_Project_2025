package com.project.Web_Project.database;

import com.project.Web_Project.dto.User;
import com.project.Web_Project.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDatabaseManager {
    private final UserRepository userRepository;

    @Autowired
    public UserDatabaseManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User selectUser(String email) {
        return userRepository.findByEmail(email);
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
}