package ru.stacy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stacy.dto.UserDTO;
import ru.stacy.entities.User;
import ru.stacy.services.UserService;
import ru.stacy.util.UserStateResponse;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<Long> createUser(@RequestBody User user) {
        Long id = userService.createUser(user);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserStateResponse> updateUserState(@PathVariable Long id, @RequestParam("state") Boolean userState) {
        UserStateResponse userStateResponse = userService.updateUserState(id, userState);
        return ResponseEntity.ok(userStateResponse);
    }

    @GetMapping("/users/statistics")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<List<UserDTO>> getAllUsersByParameters(
            @RequestParam(name = "unixTimestamp", required = false) Long unixTimestamp,
            @RequestParam(name = "userState", required = false) Boolean userState) {

        List<UserDTO> users;

        if(unixTimestamp != null) {
            if(userState != null) {
                users = userService.getAllUsersByUserStateAndTimestampAfter(userState, unixTimestamp);
                return ResponseEntity.ok(users);
            }
            users = userService.getAllUsersByUnixTimestampAfter(unixTimestamp);
            return ResponseEntity.ok(users);
        } else if(userState != null) {
            users = userService.getAllUsersByUserState(userState);
            return ResponseEntity.ok(users);
        }
        users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
