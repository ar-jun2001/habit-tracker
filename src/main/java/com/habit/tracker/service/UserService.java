package com.habit.tracker.service;

import com.habit.tracker.entity.HabitEntity;
import com.habit.tracker.entity.UserEntity;
import com.habit.tracker.repository.HabitRepository;
import com.habit.tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HabitRepository habitRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public UserEntity createUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserEntity updateUser(Long id, UserEntity userDetails) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);


        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            user.setUsername(userDetails.getUsername());
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            user.setEmail(userDetails.getEmail());
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setRole(userDetails.getRole());
//            user.setHabits(userDetails.getHabits());
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
//            UserEntity user = userRepository.findById(id).orElse(null);
//            for(HabitEntity habit : user.getHabits()) {
//                habitRepository.delete(habit);
//            }
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean authenticateUser(String username, String rawPassword) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }
}


