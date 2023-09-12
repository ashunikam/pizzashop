package com.in.pizza.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.in.pizza.demo.model.Drinks;
import com.in.pizza.demo.model.Pizza;
import com.in.pizza.demo.repository.PizzaRepository;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/v1")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;
    
    @CrossOrigin(origins="http://localhost:4200/")
    @GetMapping("/pizza/getAll")
    public  List<Pizza>listPizzas(HttpServletResponse response) throws IOException {
    	System.out.println("In pizza");
        List<Pizza> pizzas = pizzaRepository.findAll();
        
        return pizzas;
    }
    @GetMapping("/pizza/{id}")
    @CrossOrigin(origins="http://localhost:4200/")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        
        if (pizza!=null) {
            return ResponseEntity.ok(pizza);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @CrossOrigin(origins="http://localhost:4200/")
	@PostMapping("/pizza/addPizza")
	public Pizza createEmployee(@RequestBody Pizza pizza) {
		return pizzaRepository.save(pizza);
	}
    
    
    @CrossOrigin(origins="http://localhost:4200/")
	@PutMapping("/pizza/{id}")
	public ResponseEntity<Pizza> updatePizza(@PathVariable Long id, @RequestBody Pizza pizzaDetails) {
    	Pizza pizza=pizzaRepository.findById(id).orElse(null);
    	pizza.setName(pizzaDetails.getName());
    	pizza.setPrice(pizzaDetails.getPrice());
		pizza.setDescription(pizzaDetails.getDescription());
		Pizza updatedPizza=pizzaRepository.save(pizza);
		return ResponseEntity.ok(updatedPizza);
	}
	
	@CrossOrigin(origins="http://localhost:4200/")
	@DeleteMapping("/pizza/{id}")
	public ResponseEntity<Map<String, Boolean>> deletePizza(@PathVariable Long id){
		Pizza pizza=pizzaRepository.findById(id).orElse(null);
		pizzaRepository.delete(pizza);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	

	
    

}
