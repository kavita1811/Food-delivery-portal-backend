package com.zomato.zomato.exception;

public class NoRestaurantsAvailableException extends RuntimeException{

    public NoRestaurantsAvailableException(String noRestaurantsAreAvailable) {
        super(noRestaurantsAreAvailable);
    }
}
