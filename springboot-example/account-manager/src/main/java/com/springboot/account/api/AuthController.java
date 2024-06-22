package com.springboot.account.api;

import com.springboot.account.domain.UserCreateRequestDto;
import com.springboot.account.domain.UserLoginDto;
import com.springboot.account.domain.entities.User;
import com.springboot.account.security.JwtTokenProvider;
import com.springboot.account.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    private final AccountService accountService;

    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, AccountService accountService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto userLoginDto) {
        UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userLoginDto.getUserName(), userLoginDto.getPassword());
        Authentication auth = authenticationManager.authenticate(authtoken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return "Bearer " + jwtToken;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        User user = accountService.getOneUserByUsername(userCreateRequestDto.getUserName());
        if (user != null) {
            return new ResponseEntity<>("User already in use.", HttpStatus.BAD_REQUEST);
        }
        accountService.createUserPity(userCreateRequestDto.getUserName(), passwordEncoder.encode(userCreateRequestDto.getPassword()));
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }
}
