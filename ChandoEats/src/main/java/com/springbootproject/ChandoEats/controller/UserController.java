package com.springbootproject.ChandoEats.controller;

import com.springbootproject.ChandoEats.Exception.UserException;
import com.springbootproject.ChandoEats.model.User;
import com.springbootproject.ChandoEats.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException {
         User user=userService.findUserProfileByJwt(jwt);
         user.setPassword(null);

         return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

}

