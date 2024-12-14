package com.zomato.zomato.service;

import com.zomato.zomato.entity.Address;
import com.zomato.zomato.entity.Customer;
import com.zomato.zomato.repository.CustomerRepository;
import com.zomato.zomato.request.LoginRequest;
import com.zomato.zomato.request.SignUpRequest;
import com.zomato.zomato.response.AddressResponse;
import com.zomato.zomato.response.LoginResponse;
import com.zomato.zomato.response.SignUpResponse;
import com.zomato.zomato.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private CustomerRepository customerRepository;
//Sign-Up
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        log.info("In signUp service");
        validateSignUpRequest(signUpRequest);
        Customer customer = customerRepository.findByEmail(signUpRequest.getEmail());
        if (Objects.nonNull(customer)) {
            throw new RuntimeException("Customer already Exists");
        }
        customer = createNewCustomer(signUpRequest, customer);
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setCustomerId(customer.getId());
        signUpResponse.setMessage("Customer Created Successfully");
        return signUpResponse;
    }
//Create new customer
    private Customer createNewCustomer(SignUpRequest signUpRequest, Customer customer) {
        log.info("Creating a new Customer ");
        customer = new Customer();
        customer.setEmail(signUpRequest.getEmail());
        customer.setPassword(signUpRequest.getPassword());
        customer.setName(signUpRequest.getName());
        customer.setContact(signUpRequest.getContact());
        List<Address> addresses = new ArrayList<>();
        Address address = new Address();
        address.setFlat(signUpRequest.getFlatNo());
        address.setArea(signUpRequest.getArea());
        address.setCity(signUpRequest.getCity());
        address.setPinCode(signUpRequest.getPinCode());
        address.setState(signUpRequest.getState());
        addresses.add(address);

        customer.setAddresses(addresses);

        customerRepository.save(customer);
        return customer;
    }

    private boolean validateSignUpRequest(SignUpRequest signUpRequest) {
        if (!commonUtils.validateEmail(signUpRequest.getEmail())
                || !commonUtils.validatePassword(signUpRequest.getPassword())
                || !signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())
                || !commonUtils.validatePhoneNumber(signUpRequest.getContact())) {
            log.info("Invalid request");
            throw new RuntimeException("Invalid Request");
        }
        return true;
    }
//Login method
    public LoginResponse login(LoginRequest loginRequest) {
        if (commonUtils.validateEmail(loginRequest.getEmail())
                || commonUtils.validatePassword(loginRequest.getPassword())) {
            Customer customer = customerRepository.findByEmail(loginRequest.getEmail());
            if (customer == null) {
                throw new RuntimeException("Customer does not exist");
            } else {
                String token = UUID.randomUUID().toString();
                customer.setToken(token);
                customerRepository.save(customer);
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setEmail(customer.getEmail());
                loginResponse.setName(customer.getName());
                loginResponse.setContact(customer.getContact());
                loginResponse.setToken(customer.getToken());
                return loginResponse;
            }
        } else {
            throw new RuntimeException("Invalid ID or Password");
        }
    }
//TO display Address List
    public List<AddressResponse> getAllAddressesByCustomerId(Long customerId) {
        try {
            Optional<Customer> customer = customerRepository.findById(customerId);
            if (customer.isEmpty()) {
                throw new RuntimeException("No Customer available for this ID");
            }
            List<Address> addressList = customer.get().getAddresses();
            if(customer.get().getAddresses().isEmpty()){
                throw new RuntimeException("No Addresses available");
            }
            List<AddressResponse> addressResponses = addressList.stream().map(address -> {
                AddressResponse addressResponse = new AddressResponse();
                addressResponse.setId(address.getId());
                addressResponse.setFlat(address.getFlat());
                addressResponse.setArea(address.getArea());
                addressResponse.setCity(address.getCity());
                addressResponse.setState(address.getState());
                addressResponse.setPinCode(address.getPinCode());
                return addressResponse;
            }).collect(Collectors.toList());
            return addressResponses;
        } catch (NullPointerException nullPointerException) {
            log.error("Null Pointer Exception occurred", nullPointerException);
            throw new NullPointerException("Something went wrong");
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw new RuntimeException(e);
        }
    }
}
