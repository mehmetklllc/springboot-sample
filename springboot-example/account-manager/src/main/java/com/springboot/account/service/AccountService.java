package com.springboot.account.service;

import com.springboot.account.domain.UserCreateRequestDto;
import com.springboot.account.domain.entities.User;

import java.util.Optional;
import java.util.UUID;


public interface AccountService {
    User createUser(UserCreateRequestDto userCreateRequestDto);

    User createUserPity(String username ,String password);

    User findByUsername(String userName);

    Optional<User> findById(UUID id);

    User getOneUserByUsername(String userName);
}
