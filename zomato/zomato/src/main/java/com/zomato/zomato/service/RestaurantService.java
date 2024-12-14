package com.zomato.zomato.service;

import com.zomato.zomato.entity.Dish;
import com.zomato.zomato.entity.Restaurant;
import com.zomato.zomato.exception.NoRestaurantsAvailableException;
import com.zomato.zomato.repository.RestaurantRepository;
import com.zomato.zomato.response.DishResponse;
import com.zomato.zomato.response.RestaurantResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    private RestaurantRepository restaurantRepository;
    //To display Restaurant list
    public List<RestaurantResponse> getAllRestaurants() {
        try {
            List<Restaurant> restaurantList;
            restaurantList = (List<Restaurant>) restaurantRepository.findAll();
            if (restaurantList.isEmpty()) {
                throw new NoRestaurantsAvailableException("No restaurants are available");
            }
            List<RestaurantResponse> restaurantResponses = restaurantList.stream().map(restaurant -> {
                RestaurantResponse restaurantResponse = new RestaurantResponse();
                restaurantResponse.setId(restaurant.getId());
                restaurantResponse.setName(restaurant.getName());
                restaurantResponse.setContact(restaurant.getContact());
                restaurantResponse.setEmail(restaurant.getEmail());
                restaurantResponse.setRating(restaurant.getRating());
                restaurantResponse.setCuisineType(restaurant.getCuisineType());
                return restaurantResponse;
            }).collect(Collectors.toList());
            return restaurantResponses;
        } catch (NullPointerException nullPointerException) {
            log.error("Null Pointer Exception occurred", nullPointerException);
            throw new NullPointerException("Something went wrong");
        }
        //Custom NoRestaurantsAvailableException
        catch (NoRestaurantsAvailableException noRestaurantsAvailableException) {
            log.error("No Restaurants Found Exception occurred", noRestaurantsAvailableException);
            throw noRestaurantsAvailableException;
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw new RuntimeException("Something went wrong");
        }
    }
    //To display Dish List
    public List<DishResponse> getAllDishesByRestaurantId(Long restaurantId) {
        try {
            Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
            if (restaurant.isEmpty()) {
                throw new RuntimeException("No restaurant available for this ID");
            }
            List<Dish> dishList = restaurant.get().getDishes();
            if(restaurant.get().getDishes().isEmpty()){
                throw new RuntimeException("No Dishes available");
            }
            List<DishResponse> dishResponses = dishList.stream().map(dish -> {
                DishResponse dishResponse = new DishResponse();
                dishResponse.setId(dish.getId());
                dishResponse.setName(dish.getName());
                dishResponse.setAmount(dish.getAmount());
                return dishResponse;
            }).collect(Collectors.toList());
            return dishResponses;
        } catch (NullPointerException nullPointerExceptiongit) {
            log.error("Null Pointer Exception occurred", nullPointerException);
            throw new NullPointerException("Something went wrong");
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw new RuntimeException(e);
        }
    }
}

