package com.zomato.zomato.request;

import java.util.Map;

public class OrderSummaryRequest {

    private long restaurantId;
    Map<Long, Integer> dishQuantityMap;

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Map<Long, Integer> getDishQuantityMap() {
        return dishQuantityMap;
    }

    public void setDishQuantityMap(Map<Long, Integer> dishQuantityMap) {
        this.dishQuantityMap = dishQuantityMap;
    }
}


