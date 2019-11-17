package quizapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quizapi.model.User;
import quizapi.service.UserService;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    @Autowired
    private UserService userService;


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("{id}")
    public User getCurrentUser(@PathVariable long id) {
        User userSummary = userService.findById(id);
        return userSummary;
    }

}
