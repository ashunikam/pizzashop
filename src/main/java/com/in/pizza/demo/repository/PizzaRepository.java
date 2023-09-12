package com.in.pizza.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in.pizza.demo.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long>{

}
