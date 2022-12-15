package org.pizzeria.italy.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.pizzeria.italy.pojo.Pizza;
import org.pizzeria.italy.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepo pizzaRepo;

	public void save(Pizza pizza) {
		pizzaRepo.save(pizza);
	}

	public List<Pizza> findAll() {
		return pizzaRepo.findAll();
	}

	public Optional<Pizza> findPizzaById(int id) {

		return pizzaRepo.findById(id);
	}

	public void deleteById(int id) {

		pizzaRepo.deleteById(id);
	}

	public List<Pizza> findByName(String name) {
		return pizzaRepo.findByNameContainingIgnoreCase(name);
	}
	
	@Transactional
	public List<Pizza> findAllIngredients() {
		List<Pizza> pizzas = pizzaRepo.findAll();
		
		for (Pizza p : pizzas) {
			Hibernate.initialize(p.getIngredients());
		}
		
		return pizzas;
		
	}

}
