package com.in.pizza.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.in.pizza.demo.repository.SideRepository;

@RestController
@RequestMapping("/api/v1")
public class SideController {
    @Autowired
    private SideRepository sideRepository;
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/side/getAll")
    public List<Side> listSides(Model model) {
        List<Side> sides = sideRepository.findAll();
        return sides;
    }
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/side/{id}")
   
    public ResponseEntity<Side> getSideById(@PathVariable Long id) {
        Side side = sideRepository.findById(id).orElse(null);
        
        if (side!=null) {
            return ResponseEntity.ok(side);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @CrossOrigin(origins="http://localhost:4200/")
   	@PostMapping("/side/addSide")
   	public Side createSide(@RequestBody Side side) {
   		return sideRepository.save(side);
   	}
    
    @CrossOrigin(origins="http://localhost:4200/")
	@PutMapping("/side/{id}")
	public ResponseEntity<Side> updateSide(@PathVariable Long id, @RequestBody Drinks sideDetails) {
    	Side side=sideRepository.findById(id).orElse(null);
    	side.setName(sideDetails.getName());
    	side.setPrice(sideDetails.getPrice());
		Side updatedSide=sideRepository.save(side);
		return ResponseEntity.ok(updatedSide);
	}
	
	@CrossOrigin(origins="http://localhost:4200/")
	@DeleteMapping("/side/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteSide(@PathVariable Long id){
		Side side=sideRepository.findById(id).orElse(null);
		sideRepository.delete(side);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
        
    
}