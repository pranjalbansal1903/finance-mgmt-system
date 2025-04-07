package com.example.project_1.services;

import com.example.project_1.MyUserDetailsService;
import com.example.project_1.models.User;
import com.example.project_1.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }


//    public User getUserById(Long userId) {
//        return userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }


//    public User getUserIdByUsername(String authenticatedUsername) {
//        return userRepository.findByUsername(authenticatedUsername)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//    }



    public User getUserByUsername(String authenticatedUsername) {

            return userRepository.findByUsername(authenticatedUsername)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

    public void deleteUserById(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User with ID " + userId + " does not exist.");
        }
        userRepository.deleteById(userId);
    }
}