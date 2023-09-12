package com.in.pizza.demo.controller;

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
import com.in.pizza.demo.model.Side;
import com.in.pizza.demo.repository.DrinkRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DrinkController {
    @Autowired
    private DrinkRepository drinkRepository;
    @CrossOrigin(origins="http://localhost:4200")

    @GetMapping("/drink/getAll")
    public  List<Drinks> listDrinks(Model model) {
        List<Drinks> drinks = drinkRepository.findAll();
        
        return drinks;
    }
    @CrossOrigin(origins="http://localhost:4200/")
    @GetMapping("/drink/{id}")
  
    public ResponseEntity<Drinks> getDrinkById(@PathVariable Long id) {
        Drinks drink = drinkRepository.findById(id).orElse(null);
        
        if (drink!=null) {
            return ResponseEntity.ok(drink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @CrossOrigin(origins="http://localhost:4200/")
	@PostMapping("/drink/addDrink")
	public Drinks createDrink(@RequestBody Drinks drink) {
		return drinkRepository.save(drink);
	}
    
    
    @CrossOrigin(origins="http://localhost:4200/")
	@PutMapping("/drink/{id}")
	public ResponseEntity<Drinks> updatePizza(@PathVariable Long id, @RequestBody Drinks drinkDetails) {
    	Drinks drink=drinkRepository.findById(id).orElse(null);
    	drink.setName(drinkDetails.getName());
    	drink.setPrice(drinkDetails.getPrice());
		Drinks updatedDrink=drinkRepository.save(drink);
		return ResponseEntity.ok(updatedDrink);
	}
	
	@CrossOrigin(origins="http://localhost:4200/")
	@DeleteMapping("/drink/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteDrink(@PathVariable Long id){
		Drinks drink=drinkRepository.findById(id).orElse(null);
		drinkRepository.delete(drink);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}