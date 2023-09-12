package com.in.pizza.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in.pizza.demo.model.Drinks;

public interface DrinkRepository extends JpaRepository<Drinks, Long>{

}
