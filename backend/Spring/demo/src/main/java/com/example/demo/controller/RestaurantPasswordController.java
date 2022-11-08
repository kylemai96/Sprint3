package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RestaurantPassword;
import com.example.demo.repository.RestaurantPasswordRepository;

@RestController
public class RestaurantPasswordController {

	@Autowired
	private RestaurantPasswordRepository restaurantPasswordRepository;
	
	@PostMapping("/restaurantPassword")
	public RestaurantPassword saveRestaurantPassword(@RequestBody RestaurantPassword restaurantPassword) {
		return restaurantPasswordRepository.save(restaurantPassword);
	}
	
	@GetMapping("/restaurantPassword/{id}")
	public RestaurantPassword getRestaurantPasswordbyId(@PathVariable("id") String id) {
		return restaurantPasswordRepository.getRestaurantPasswordById(id);
	}
	
	@GetMapping("/restaurantPassword/password/{password}")
	public List<RestaurantPassword> getRestaurantPasswordByPassword(@PathVariable("password") String password) {
		return restaurantPasswordRepository.getRestaurantPasswordByPassword(password);
	}
	
	@DeleteMapping("/restaurantPassword/{id}")
	public String deleteRestaurantPassword(@PathVariable("id") String id) {
		return restaurantPasswordRepository.delete(id);
	}
	
	@PutMapping("/restaurantPassword/{id}")
	public String updateRestaurantPassword(@PathVariable("id") String id, @RequestBody RestaurantPassword restaurantPassword) {
		return restaurantPasswordRepository.update(id, restaurantPassword);
	}
}
