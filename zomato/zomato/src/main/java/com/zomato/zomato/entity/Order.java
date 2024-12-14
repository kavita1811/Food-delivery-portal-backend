package com.zomato.zomato.entity;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long restaurant_id;
    private String restaurant_name;
    private Long customer_id;
    private String customer_name;
    private String contact;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Address> addresses;
}
