package com.springboot.account.api;

import com.springboot.account.domain.UserCreateRequestDto;
import com.springboot.account.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    @PostMapping("/create")
    public String createAccount(@RequestBody UserCreateRequestDto userCreateRequestDto){
        accountService.createUser(userCreateRequestDto);
        return "success";
    }
}
