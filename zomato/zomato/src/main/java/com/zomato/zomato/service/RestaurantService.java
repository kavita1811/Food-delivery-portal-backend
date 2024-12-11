package com.zomato.zomato.service;

import com.zomato.zomato.controller.RestaurantController;
import com.zomato.zomato.entity.Dish;
import com.zomato.zomato.entity.Restaurant;
import com.zomato.zomato.repository.RestaurantRepository;
import com.zomato.zomato.response.DishResponse;
import com.zomato.zomato.response.RestaurantResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    private RestaurantRepository restaurantRepository;

    //Restaurant list
    public List<RestaurantResponse> getAllRestaurants() {
        try {
            List<Restaurant> restaurantList;
            restaurantList = (List<Restaurant>) restaurantRepository.findAll();
            if(restaurantList.isEmpty()){
                throw new RuntimeException("No restaurants are available");
            }
            List<RestaurantResponse> restaurantResponses = restaurantList.stream().map(restaurant -> {
                RestaurantResponse restaurantResponse = new RestaurantResponse();
                restaurantResponse.setId(restaurant.getId());
                restaurantResponse.setName(restaurant.getName());
                restaurantResponse.setContact(restaurant.getContact());
                restaurantResponse.setEmail(restaurant.getEmail());
                restaurantResponse.setRating(restaurant.getRating());
                restaurantResponse.setCuisineType(restaurant.getCuisineType());

                List<Dish> dishList = restaurant.getDishes();
                List<DishResponse> dishResponses = dishList.stream().map(dish -> {
                    DishResponse dishResponse = new DishResponse();
                    dishResponse.setId(dish.getId());
                    dishResponse.setName(dish.getName());
                    dishResponse.setAmount(dish.getAmount());
                    return dishResponse;
                }).collect(Collectors.toList());
                restaurantResponse.setDishes(dishResponses);
                return restaurantResponse;
            }).collect(Collectors.toList());
            return restaurantResponses;
        } catch (NullPointerException nullPointerException) {
            log.error("Null Pointer Exception occurred", nullPointerException);
            throw new NullPointerException("Something went wrong");
        }
        //TODO Add Custom NoRestaurantAvailableException
        catch (Exception e) {
            log.error("Exception occurred", e);
            throw new RuntimeException("Something went wrong");
        }


    }
}

