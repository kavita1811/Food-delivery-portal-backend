package com.zomato.zomato.service;

import com.zomato.zomato.entity.Dish;
import com.zomato.zomato.entity.Restaurant;
import com.zomato.zomato.repository.DishRepository;
import com.zomato.zomato.repository.RestaurantRepository;
import com.zomato.zomato.request.OrderSummaryRequest;
import com.zomato.zomato.response.OrderSummaryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private DishRepository dishRepository;

    public OrderSummaryResponse getOrderSummary(OrderSummaryRequest orderSummaryRequest) {
        log.info("In getOrderSummary service");
        try {
            Optional<Restaurant> restaurant = restaurantRepository.findById(orderSummaryRequest.getRestaurantId());
            if (restaurant.isEmpty()) {
                log.error("Restaurant does not exist");
                throw new RuntimeException("Restaurant does not exist");
            } else if (restaurant.get().getDishes().isEmpty()) {
                log.error("Dishes do not exist for this Restaurant");
                throw new RuntimeException("Dishes do not exist for this Restaurant");
            } else {
                List<Dish> dishList = restaurant.get().getDishes();
                OrderSummaryResponse orderSummaryResponse = new OrderSummaryResponse();
                //Filter out the matching dishes of dishListMap from dishList
                Map<Long, Integer> dishListMap = orderSummaryRequest.getDishQuantityMap();
                long totalAmount = dishList.stream().filter(dish -> dishListMap.containsKey(dish.getId()))
                        .map(dish -> dishListMap.get(dish.getId()) * dish.getAmount())
                        .mapToInt(Integer::intValue) // Convert to IntStream
                        .sum();
                orderSummaryResponse.setTotalAmount(totalAmount);
                return orderSummaryResponse;
            }
        } catch (NullPointerException nullPointerException) {
            log.error("Null Pointer Exception occurred", nullPointerException);
            throw new NullPointerException("Something went wrong");
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw new RuntimeException(e);
        }
    }
}
