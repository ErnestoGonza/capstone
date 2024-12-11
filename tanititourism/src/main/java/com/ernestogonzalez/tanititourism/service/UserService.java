package com.ernestogonzalez.tanititourism.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ernestogonzalez.tanititourism.entity.User;
import com.ernestogonzalez.tanititourism.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveAll(List<User> users) {
        userRepository.saveAll(users);
    }

    public String getUsersFirstName(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Username " + username + " not found");
        }
        User user = optionalUser.get();
        return user.getFirstName();
    }

    public User findUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new RuntimeException("Username name not found for user :: " + username);
        }
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}
