package com.example.coinmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.coinmanager.repository.UserRepository;
import com.example.coinmanager.model.UserEntity;
import com.example.coinmanager.dto.UsersDto;

@Service
public class UsersService {
    @Autowired
    private UserRepository usersRepository;

    public UserEntity createUser(UsersDto data) {
        UserEntity user = new UserEntity(data);
        return usersRepository.save(user);
    }

}
