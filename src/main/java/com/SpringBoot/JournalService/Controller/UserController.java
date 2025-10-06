package com.SpringBoot.JournalService.Controller;

import com.SpringBoot.JournalService.Entity.User;
import com.SpringBoot.JournalService.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable ObjectId id) {
        Optional<User> user = userService.findUserById1(id);
        if(user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        user.setUsername(!updatedUser.getUsername().isEmpty() ? updatedUser.getUsername() : user.getUsername());
        user.setPassword(!updatedUser.getPassword().isEmpty() ? updatedUser.getPassword() : user.getPassword());
        userService.saveNewUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        userService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
