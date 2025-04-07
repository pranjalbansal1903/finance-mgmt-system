//package com.example.project_1.controllers;
//
//import com.example.project_1.models.User;
//import com.example.project_1.services.UserService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//
//
//
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//    @PostMapping("/create")
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        return ResponseEntity.ok(userService.createUser(user));
//    }
//
//
//    @DeleteMapping("/delete/{userId}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
//
//
//        userService.deleteUserById(userId);
//        return ResponseEntity.ok("User with ID " + userId + " has been deleted.");
//    }
//
//
//
//}