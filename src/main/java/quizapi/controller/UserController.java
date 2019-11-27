package quizapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quizapi.model.User;
import quizapi.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody String username) {
        return ResponseEntity.ok(userService.findOrCreateByUsername(username));
    }

    @GetMapping("/{id}")
    public User getCurrentUser(@PathVariable long id) {
        return userService.findById(id);
    }

}
