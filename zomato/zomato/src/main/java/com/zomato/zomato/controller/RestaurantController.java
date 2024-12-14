package com.zomato.zomato.controller;

import com.zomato.zomato.response.DishResponse;
import com.zomato.zomato.response.RestaurantResponse;
import com.zomato.zomato.service.RestaurantService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
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

    @GetMapping("/{restaurantId}")
    public List<DishResponse> getAllDishesByRestaurantId(@PathVariable Long restaurantId) {
        log.info("Request Till Restaurant Controller");
        List<DishResponse> dishResponse = restaurantService.getAllDishesByRestaurantId(restaurantId);
        log.info("Successfully fetched the dish list");
        return dishResponse;
    }
}
