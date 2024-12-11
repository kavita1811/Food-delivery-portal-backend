package com.zomato.zomato.controller;

import com.zomato.zomato.response.RestaurantResponse;
import com.zomato.zomato.service.RestaurantService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurant-list")
    public List<RestaurantResponse> getAllRestaurants() {
        log.info("Request Till Restaurant Controller");
        List<RestaurantResponse> restaurantResponse = restaurantService.getAllRestaurants();
        log.info("Successfully fetched the restaurant list");
        return restaurantResponse;
    }


}
