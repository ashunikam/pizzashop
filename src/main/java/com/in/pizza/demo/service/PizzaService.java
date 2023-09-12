package com.in.pizza.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.in.pizza.demo.repository.PizzaRepository;

public class PizzaService {
	@Autowired
    private PizzaRepository pizzaRepository;

}
