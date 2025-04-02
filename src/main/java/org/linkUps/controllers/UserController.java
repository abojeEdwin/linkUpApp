package org.linkUps.controllers;

import org.linkUps.data.models.User;
import org.linkUps.services.Dtos.UserRegisterationRequest;
import org.linkUps.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserServices userService;

    @PostMapping("/adduser")
    public User register(@RequestBody UserRegisterationRequest request){
       return userService.register(request.getUser(),request.getProfile());
    }
}
