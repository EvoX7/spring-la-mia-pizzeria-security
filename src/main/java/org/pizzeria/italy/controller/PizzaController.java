package org.pizzeria.italy.controller;

import java.util.List;
import java.util.Optional;

import org.pizzeria.italy.pojo.Ingredient;
import org.pizzeria.italy.pojo.Pizza;
import org.pizzeria.italy.pojo.Promotion;
import org.pizzeria.italy.service.IngredientService;
import org.pizzeria.italy.service.PizzaService;
import org.pizzeria.italy.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	public PizzaService pizzaService;

	@Autowired
	private PromotionService promotionService;

	@Autowired
	private IngredientService ingredientService;

	@GetMapping
	public String getPizzas(Model model) {

		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);

		return "index";
	}

	@GetMapping("/admin/create")
	public String createNewPizza(Model model) {

		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);

		List<Promotion> promotions = promotionService.findAll();
		model.addAttribute("promotions", promotions);

		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredient", ingredients);

		return "pizza-create";
	}

	@PostMapping("/admin/create")
	public String getStorePizza(@Valid Pizza pizza, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {

			System.err.println("---------------------- START ERROR ----------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("---------------------- END ERROR ------------------------");

			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

			return "redirect:/pizzas/admin/create";
		}

		try {

			pizzaService.save(pizza);
		} catch (Exception e) {

			redirectAttributes.addFlashAttribute("catchError", e.getMessage());

			return "redirect:/pizzas/admin/create";
		}

		return "redirect:/pizzas/admin/create";
	}

	@GetMapping("/admin/edit/{id}")
	public String editPizza(@PathVariable("id") int id, Model model) {

		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		Pizza pizza = optPizza.get();

		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);
		
		List<Promotion> promotions = promotionService.findAll();
		model.addAttribute("promotions", promotions);

		model.addAttribute("pizza", pizza);

		return "pizza-update";
	}

	@PostMapping("/admin/edit")
	public String updatePizza(@Valid Pizza pizza) {

		pizzaService.save(pizza);

		return "redirect:/pizzas";
	}

	@GetMapping("/admin/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {

		pizzaService.deleteById(id);

		return "redirect:/pizzas";
	}

//	SEARCH BAR 

	@GetMapping("/user/search")
	
	public String getSearchPizzaByName(Model model, @RequestParam(name = "query", required = false) String query) {

		List<Pizza> pizzas = null;
		if (query == null) {

			pizzas = pizzaService.findAll();

		} else {

			pizzas = pizzaService.findByName(query);
		}

		model.addAttribute("pizzas", pizzas);
		model.addAttribute("query", query);

		return "pizza-search";
	}
}
