package com.springbootproject.ChandoEats.controller;

import com.springbootproject.ChandoEats.Exception.UserException;
import com.springbootproject.ChandoEats.model.Notification;
import com.springbootproject.ChandoEats.model.User;
import com.springbootproject.ChandoEats.service.NotificationService;
import com.springbootproject.ChandoEats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificatonController {
    @Autowired
    private NotificationService notificationSerivce;
    @Autowired
    private UserService userService;

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> findUsersNotification(
            @RequestHeader("Authorization") String jwt) throws UserException {
        User user=userService.findUserProfileByJwt(jwt);

        List<Notification> notifications=notificationSerivce.findUserNotification(user.getId());
        return new ResponseEntity<>(notifications, HttpStatus.ACCEPTED);
    }

}
