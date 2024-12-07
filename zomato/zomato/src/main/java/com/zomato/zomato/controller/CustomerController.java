package com.zomato.zomato.controller;

import com.zomato.zomato.request.LoginRequest;
import com.zomato.zomato.request.SignUpRequest;
import com.zomato.zomato.response.LoginResponse;
import com.zomato.zomato.response.SignUpResponse;
import com.zomato.zomato.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    @PostMapping("/sign-up")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        log.info("Request Till Sign-up Controller {}", signUpRequest);
        return customerService.signUp(signUpRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        log.info("Request Till Login Controller {}", loginRequest);
        return customerService.login(loginRequest);
    }

}
