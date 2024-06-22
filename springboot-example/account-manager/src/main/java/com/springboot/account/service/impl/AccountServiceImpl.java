package com.springboot.account.service.impl;

import com.springboot.account.data.UserRepository;
import com.springboot.account.domain.UserCreateRequestDto;
import com.springboot.account.domain.entities.User;
import com.springboot.account.service.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    UserRepository userRepository;

    public AccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User createUser(UserCreateRequestDto userCreateRequestDto) {
        User user = new User();
        user.setUserName(userCreateRequestDto.getUserName());
        user.setPassword(userCreateRequestDto.getPassword());

        return userRepository.save(user);
    }

    @Override
    public User createUserPity(String username, String password) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User getOneUserByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }
}
