package com.springboot.account.service.impl;

import com.springboot.account.data.UserRepository;
import com.springboot.account.domain.entities.User;
import com.springboot.account.security.JwtUserDetails;
import com.springboot.account.service.AccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceDetailsImpl implements UserDetailsService {

    final private AccountService accountService;

    public UserServiceDetailsImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = accountService.findByUsername(username);
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(UUID id) {
        User user = accountService.findById(id).orElseThrow();
        return JwtUserDetails.create(user);
    }
}
