package com.zomato.zomato.repository;

import com.zomato.zomato.entity.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends CrudRepository<Dish,Long> {
}
