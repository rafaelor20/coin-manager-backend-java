package com.example.coinmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.coinmanager.service.UsersService;
import com.example.coinmanager.dto.UsersDto;

@RestController
@RequestMapping()
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/signup")
    public void createUser(@Valid @RequestBody UsersDto req) {
        usersService.createUser(req);
    }

}
