package com.zomato.zomato.controller;

import com.zomato.zomato.request.LoginRequest;
import com.zomato.zomato.request.SignUpRequest;
import com.zomato.zomato.response.AddressResponse;
import com.zomato.zomato.response.DishResponse;
import com.zomato.zomato.response.LoginResponse;
import com.zomato.zomato.response.SignUpResponse;
import com.zomato.zomato.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    @PostMapping("/sign-up")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        log.info("Request payload for signUp {}", signUpRequest);
        return customerService.signUp(signUpRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        log.info("Request payload for login {}", loginRequest);
        return customerService.login(loginRequest);
    }

    @GetMapping("/{customerId}")
    public List<AddressResponse> getAllAddressesByCustomerId(@PathVariable Long customerId) {
        log.info("Request Till Customer Controller");
        List<AddressResponse> addressResponse = customerService.getAllAddressesByCustomerId(customerId);
        log.info("Successfully fetched the address list");
        return addressResponse;
    }

}
