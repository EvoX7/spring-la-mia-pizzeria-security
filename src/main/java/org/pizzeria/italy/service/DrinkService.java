package org.pizzeria.italy.service;

import java.util.List;
import java.util.Optional;

import org.pizzeria.italy.pojo.Drink;
import org.pizzeria.italy.repo.DrinkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrinkService {

	@Autowired
	private DrinkRepo drinkRepo;

	public void save(Drink drink) {
		drinkRepo.save(drink);
	}

	public List<Drink> findAll() {
		return drinkRepo.findAll();
	}

	public Optional<Drink> findDrinkById(int id) {

		return drinkRepo.findById(id);
	}

	public void deleteById(int id) {

		drinkRepo.deleteById(id);
	}
	
	public List<Drink> findByName(String name) {
		return drinkRepo.findByNameContainingIgnoreCase(name);
	}

}
