package org.pizzeria.italy.service;

import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.pizzeria.italy.pojo.Ingredient;
import org.pizzeria.italy.repo.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepo ingredientRepo;

	public void save(Ingredient ingredient) {
		ingredientRepo.save(ingredient);
	}

	public Optional<Ingredient> findIngredientById(int id) {
		return ingredientRepo.findById(id);
	}

	public List<Ingredient> findAll() {
		return ingredientRepo.findAll();
	}

	public void deleteIngredientById(int id) {
		ingredientRepo.deleteById(id);
	}

	@Transactional
	public List<Ingredient> findAllPizza() {
		List<Ingredient> ingredients = ingredientRepo.findAll();

		for (Ingredient ingredient : ingredients) {
			Hibernate.initialize(ingredient.getPizzas());
		}

		return ingredients;

	}

}
