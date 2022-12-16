package org.pizzeria.italy.controller;

import java.util.List;
import java.util.Optional;

import org.pizzeria.italy.pojo.Ingredient;
import org.pizzeria.italy.pojo.Pizza;
import org.pizzeria.italy.service.IngredientService;
import org.pizzeria.italy.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	public PizzaService pizzaService;

	@GetMapping
	public String getIngredients(Model model) {

		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);

		return "index-ingredients";
	}

	@GetMapping("/admin/create")
	public String createNewIngredient(Model model) {

		Ingredient ingredient = new Ingredient();
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("ingredient", ingredient);

		return "ingredient-create";
	}

	@PostMapping("/admin/create")
	public String storeIngredient(@Valid Ingredient ingredient, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {

			System.err.println("---------------------- START ERROR ----------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("---------------------- END ERROR ------------------------");

			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

			return "redirect:/ingredient/create";
		}

		List<Pizza> ingredientsOnPizzas = ingredient.getPizzas();
		for (Pizza pizza : ingredientsOnPizzas) {
			pizza.getIngredients().add(ingredient);

		}
		ingredientService.save(ingredient);

		return "redirect:/ingredient";

	}

	@GetMapping("/admin/edit/{id}")
	public String editIngredient(@PathVariable("id") int id, Model model) {

		Optional<Ingredient> optIngredient = ingredientService.findIngredientById(id);
		Ingredient ingredient = optIngredient.get();

		model.addAttribute("ingredient", ingredient);

		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);

		return "ingredient-update";

	}

	@PostMapping("/admin/edit")
	public String updateIngredient(@Valid Ingredient ingredient) {

		Optional<Ingredient> optI = ingredientService.findIngredientById(ingredient.getId());
		Ingredient ing = optI.get();

		for (Pizza pizza : ing.getPizzas()) {
			pizza.removeIngredients(ing);
		}

		List<Pizza> ingredientOnPizzas = ingredient.getPizzas();
		for (Pizza p : ingredientOnPizzas) {
			p.addIngredients(ingredient);
		}

		ingredientService.save(ingredient);
		return "redirect:/ingredient";
	}

	@GetMapping("/admin/delete/{id}")
	public String deleteIngredient(@PathVariable("id") int id) {

		ingredientService.deleteIngredientById(id);

		return "redirect:/ingredient";
	}

}