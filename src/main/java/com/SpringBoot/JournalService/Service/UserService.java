package com.SpringBoot.JournalService.Service;

import com.SpringBoot.JournalService.Entity.User;
import com.SpringBoot.JournalService.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getListOfUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById1(ObjectId id) {
      return userRepository.findById(id);
    }

    public User findUserById2(ObjectId id) {
        for(User user : userRepository.findAll()) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}