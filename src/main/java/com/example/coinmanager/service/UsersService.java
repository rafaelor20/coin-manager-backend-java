package com.example.coinmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.coinmanager.repository.UsersRepository;
import com.example.coinmanager.model.Users;
import com.example.coinmanager.dto.UsersDto;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Users createUser(UsersDto data) {
        Users user = new Users(data);
        return usersRepository.save(user);
    }

}
