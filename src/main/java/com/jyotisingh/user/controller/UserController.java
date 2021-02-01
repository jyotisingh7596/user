package com.jyotisingh.user.controller;

import com.jyotisingh.user.entity.User;
import com.jyotisingh.user.service.UserService;
import com.jyotisingh.user.vo.ResponseTemplateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("POST /users/ RequestBody payload {}", user);
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVo getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("GET /users/id PathVariable id: {}", userId);
        return userService.getUserWithDepartment(userId);
    }
}
