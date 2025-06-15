//package com.habit.tracker.controller;
//
//import com.habit.tracker.entity.UserEntity;
//import com.habit.tracker.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//public class AdminUserController {
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public List<UserEntity> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
//        UserEntity user = userService.getUserById(id);
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping
//    public UserEntity createUser(@RequestBody UserEntity user) {
//        return userService.createUser(user);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
//        UserEntity updatedUser = userService.updateUser(id, user);
//        if (updatedUser != null) {
//            return ResponseEntity.ok(updatedUser);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        boolean deleted = userService.deleteUser(id);
//        if (deleted) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
//
