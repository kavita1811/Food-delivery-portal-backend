package com.zomato.zomato.response;

import com.zomato.zomato.entity.Address;
import com.zomato.zomato.entity.Dish;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class RestaurantResponse {
    private Long id;
    private String name;
    private Address address;
    private String contact;
    private String email;
    private String cuisineType;
    private double rating;
    private List<DishResponse> dishes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<DishResponse> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishResponse> dishes) {
        this.dishes = dishes;
    }
}
