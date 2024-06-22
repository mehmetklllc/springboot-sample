package com.springboot.account.domain;

import lombok.Data;

@Data
public class UserLoginDto {
    private String userName;
    private String password;
}
